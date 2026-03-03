const API = "http://localhost:8080/api/v1";
let token = localStorage.getItem("token");

/* ================= MESSAGE ================= */
function showMessage(msg) {
  document.getElementById("message").innerText = msg;
}

/* ================= REGISTER ================= */
async function register() {
  const body = {
    name: regName.value.trim(),
    email: regEmail.value.trim(),
    password: regPassword.value.trim(),
  };

  try {
    const res = await fetch(`${API}/auth/register`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    });

    if (!res.ok) {
      const text = await res.text();
      showMessage("❌ " + text);
      return;
    }

    showMessage("✅ Registered successfully — please login");
  } catch (err) {
    showMessage("❌ Server error");
    console.error(err);
  }
}

/* ================= LOGIN ================= */
async function login() {
  const body = {
    email: loginEmail.value.trim(),
    password: loginPassword.value.trim(),
  };

  try {
    const res = await fetch(`${API}/auth/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    });

    if (!res.ok) {
      showMessage("❌ Login failed");
      return;
    }

    const data = await res.json();

    token = data.token;
    localStorage.setItem("token", token);

    showMessage("✅ Login successful");

    showDashboard();
    loadTasks();
  } catch (err) {
    showMessage("❌ Server error");
    console.error(err);
  }
}

/* ================= SHOW DASHBOARD ================= */
function showDashboard() {
  document.getElementById("dashboard").classList.remove("hidden");
  document.getElementById("authSection").classList.add("hidden");
  document.getElementById("logoutBtn").classList.remove("hidden");
}

/* ================= LOGOUT ================= */
function logout() {
  localStorage.removeItem("token");
  location.reload();
}

/* ================= CREATE TASK ================= */
async function createTask() {
  const titleVal = taskTitle.value.trim();
  const descVal = taskDesc.value.trim();
  const statusVal = taskStatus.value;

  if (!titleVal) {
    showMessage("⚠️ Task title required");
    return;
  }

  const body = {
    title: titleVal,
    description: descVal,
    status: statusVal,
  };

  try {
    const res = await fetch(`${API}/tasks`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
      body: JSON.stringify(body),
    });

    if (!res.ok) {
      showMessage("❌ Task creation failed");
      return;
    }

    taskTitle.value = "";
    taskDesc.value = "";

    loadTasks();
  } catch (err) {
    console.error(err);
    showMessage("❌ Server error");
  }
}

/* ================= LOAD TASKS ================= */
async function loadTasks() {
  try {
    const res = await fetch(`${API}/tasks`, {
      headers: {
        Authorization: "Bearer " + token,
      },
    });

    if (!res.ok) return;

    const tasks = await res.json();
    const list = document.getElementById("taskList");
    list.innerHTML = "";

    tasks.forEach((t) => {
      const li = document.createElement("li");
      li.className = "task-item";
      li.innerHTML = `
        <span>${t.title} — ${t.status}</span>
        <button class="delete-btn" onclick="deleteTask(${t.id})">Delete</button>
      `;
      list.appendChild(li);
    });
  } catch (err) {
    console.error(err);
  }
}

/* ================= DELETE ================= */
async function deleteTask(id) {
  try {
    await fetch(`${API}/tasks/${id}`, {
      method: "DELETE",
      headers: {
        Authorization: "Bearer " + token,
      },
    });

    loadTasks();
  } catch (err) {
    console.error(err);
  }
}

/* ================= AUTO LOGIN ================= */
if (token) {
  showDashboard();
  loadTasks();
}
