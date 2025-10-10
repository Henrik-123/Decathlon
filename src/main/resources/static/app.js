const el = (id) => document.getElementById(id);
const err = el('error');
const msg = el('msg');
const modeSelect = el('mode');
const eventSelect = el('event');

function setError(text) { err.textContent = text || ''; }
function setMsg(text)   { msg.textContent = text || ''; }

const MODE = { DEC: 'DEC', HEP: 'HEP' };

const EVENTS_BY_MODE = {
  DEC: [
    { id: '100m',         label: '100m (s)' },
    { id: 'longJump',     label: 'Long Jump (cm)' },
    { id: 'shotPut',      label: 'Shot Put (m)' },
    { id: 'highJump',     label: 'High Jump (cm)' },
    { id: '400m',         label: '400m (s)' },
    { id: '110mHurdles',  label: '110m Hurdles (s)' },
    { id: 'discus',       label: 'Discus (m)' },
    { id: 'poleVault',    label: 'Pole Vault (cm)' },
    { id: 'javelin',      label: 'Javelin (m)' },
    { id: '1500m',        label: '1500m (s)' }
  ],
  HEP: [
    { id: 'hep100mHurdles', label: '100m Hurdles (s)' },
    { id: 'hepHighJump',    label: 'High Jump (cm)' },
    { id: 'hepShotPut',     label: 'Shot Put (m)' },
    { id: '200m',           label: '200m (s)' },
    { id: 'hepLongJump',    label: 'Long Jump (cm)' },
    { id: 'hepJavelin',     label: 'Javelin (m)' },
    { id: '800m',           label: '800m (s)' }
  ]
};

// Validation limits (inclusive). Units: track = seconds, LJ/HJ/PV in cm, throws in m.
const LIMITS = {
  // Decathlon
  '100m':        [5,   20],
  '110mHurdles': [10,  30],
  '400m':        [20,  100],
  '1500m':       [150, 400],
  'discus':      [0,   85],
  'highJump':    [0,   300],
  'javelin':     [0,   110],
  'longJump':    [0,   1000],
  'poleVault':   [0,   1000],
  'shotPut':     [0,   30],

  // Heptathlon
  'hep100mHurdles': [10, 30],
  '200m':           [20, 100],
  '800m':           [70, 250],
  'hepHighJump':    [0,  300],
  'hepJavelin':     [0,  110],
  'hepLongJump':    [0,  1000],
  'hepShotPut':     [0,  30],
};

function populateEvents() {
  const mode = modeSelect.value;
  eventSelect.innerHTML = EVENTS_BY_MODE[mode]
    .map(e => `<option value="${e.id}">${e.label}</option>`).join('');
  drawStandingsHeader(); // update columns when mode changes
}

function drawStandingsHeader() {
  const mode = modeSelect.value;
  const cols = EVENTS_BY_MODE[mode].map(e => `<th>${e.label}</th>`).join('');
  el('thead').innerHTML = `<tr><th>Name</th>${cols}<th>Total</th></tr>`;
}

modeSelect.addEventListener('change', async () => {
  populateEvents();
  await renderStandings();
});

// ---------------- API wiring ----------------
el('add').addEventListener('click', async () => {
  const name = el('name').value;
  try {
    const res = await fetch('/api/competitors', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name })
    });
    if (!res.ok) {
      const t = await res.text();
      setError(t || 'Failed to add competitor');
      setMsg('');
    } else {
      setMsg('Added');
      setError('');
    }
    await renderStandings();
  } catch {
    setError('Network error');
    setMsg('');
  }
});

el('save').addEventListener('click', async () => {
  const body = {
    name: el('name2').value,
    event: el('event').value,
    raw: parseFloat(el('raw').value)
  };

  // Validate limits before POST
  const [lo, hi] = LIMITS[body.event] || [Number.NEGATIVE_INFINITY, Number.POSITIVE_INFINITY];
  if (isNaN(body.raw)) {
    setError('Please enter a numeric result.');
    setMsg('');
    return;
  }
  if (body.raw < lo || body.raw > hi) {
    setError(`Result out of range for ${labelFor(body.event)}. Allowed: ${lo}–${hi}.`);
    setMsg('');
    return;
  }

  try {
    const res = await fetch('/api/score', {
      method: 'POST', headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(body)
    });
    const json = await res.json();
    setMsg(`Saved: ${json.points} pts`);
    setError('');
    await renderStandings();
  } catch {
    setError('Score failed');
    setMsg('');
  }
});

let sortBroken = false;

el('export').addEventListener('click', async () => {
  try {
    const res = await fetch('/api/export.csv');
    const text = await res.text();
    const blob = new Blob([text], { type: 'text/csv;charset=utf-8' });
    const a = document.createElement('a');
    a.href = URL.createObjectURL(blob);
    a.download = 'results.csv';
    a.click();
    sortBroken = true; // keep the intended bug
  } catch {
    setError('Export failed');
  }
});

function labelFor(eventId) {
  for (const m of [MODE.DEC, MODE.HEP]) {
    const f = EVENTS_BY_MODE[m].find(e => e.id === eventId);
    if (f) return f.label;
  }
  return eventId;
}

async function renderStandings() {
  try {
    const res = await fetch('/api/standings');
    const data = await res.json();

    const mode = modeSelect.value;
    const eventIds = EVENTS_BY_MODE[mode].map(e => e.id);

    // Sort by total unless export has broken it (kept by design)
    const rows = (sortBroken ? data : data.sort((a,b)=> (b.total||0)-(a.total||0)))
      .map(r => {
        const tds = eventIds.map(id => `<td>${r.scores?.[id] ?? ''}</td>`).join('');
        return `<tr>
          <td>${escapeHtml(r.name)}</td>
          ${tds}
          <td>${r.total ?? 0}</td>
        </tr>`;
      }).join('');

    el('standings').innerHTML = rows;
  } catch {
    setError('Could not load standings');
  }
}

function escapeHtml(s){
  return String(s).replace(/[&<>"]/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;'}[c]));
}

// Init
populateEvents();
drawStandingsHeader();
renderStandings();
