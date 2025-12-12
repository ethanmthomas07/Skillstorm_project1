document.addEventListener("DOMContentLoaded", () => {
    initNavigation();
    loadWarehouses();
});

function initNavigation() {
    const warehouseSection = document.getElementById("warehouseSection");
    const inventorySection = document.getElementById("inventorySection");

    document.getElementById("showWarehousesBtn").addEventListener("click", () => {
        warehouseSection.classList.add("active");
        inventorySection.classList.remove("active");
    });

    document.getElementById("showInventoryBtn").addEventListener("click", () => {
        inventorySection.classList.add("active");
        warehouseSection.classList.remove("active");
        loadWarehousesForInventory();
    });
}

/* ======================== WAREHOUSE CRUD ======================== */
async function loadWarehouses() {
    const res = await fetch("/Warehouses");
    const warehouses = await res.json();
    const tbody = document.querySelector("#warehouseTable tbody");

    tbody.innerHTML = warehouses.map(w => `
        <tr>
            <td>${w.name || "N/A"}</td>
            <td>${w.location || "N/A"}</td>
            <td>${w.maxCapacity || 0}</td>
            <td>${w.currentCapacity || 0}</td>
            <td>
                <button class="edit" onclick="editWarehouse(${w.id})">Edit</button>
                <button class="delete" onclick="deleteWarehouse(${w.id})">Delete</button>
            </td>
        </tr>
    `).join("");

    document.getElementById("addWarehouseBtn").onclick = () => openWarehouseModal();
    document.getElementById("warehouseSearch").oninput = (e) => filterTable(e.target.value, tbody);
}

function filterTable(query, tbody) {
    query = query.toLowerCase();
    Array.from(tbody.querySelectorAll("tr")).forEach(tr => {
        tr.style.display = tr.textContent.toLowerCase().includes(query) ? "" : "none";
    });
}

function openWarehouseModal(warehouse = {}) {
    const modal = document.getElementById("modal");
    const modalBody = document.getElementById("modalBody");

    modalBody.innerHTML = `
        <h3>${warehouse.id ? "Edit" : "Add"} Warehouse</h3>
        <form id="warehouseForm">
            <label>Name</label>
            <input type="text" name="name" value="${warehouse.name || ""}" required>
            <label>Location</label>
            <input type="text" name="location" value="${warehouse.location || ""}" required>
            <label>Max Capacity</label>
            <input type="number" name="maxCapacity" value="${warehouse.maxCapacity || 0}" required>
            <button type="submit">${warehouse.id ? "Update" : "Create"}</button>
        </form>
    `;

    document.getElementById("warehouseForm").onsubmit = async (e) => {
        e.preventDefault();
        const formData = Object.fromEntries(new FormData(e.target).entries());
        formData.maxCapacity = parseInt(formData.maxCapacity);

        if (warehouse.id) {
            await fetch(`/Warehouses/${warehouse.id}`, { method: "PUT", headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(formData) });
        } else {
            await fetch(`/Warehouses`, { method: "POST", headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(formData) });
        }

        modal.style.display = "none";
        loadWarehouses();
    };

    openModal();
}

async function editWarehouse(id) {
    const res = await fetch(`/Warehouses/${id}`);
    const warehouse = await res.json();
    openWarehouseModal(warehouse);
}

async function deleteWarehouse(id) {
    if (confirm("Are you sure you want to delete this warehouse?")) {
        await fetch(`/Warehouses/${id}`, { method: "DELETE" });
        loadWarehouses();
    }
}

/* ======================== INVENTORY CRUD ======================== */
async function loadWarehousesForInventory() {
    const res = await fetch("/Warehouses");
    const warehouses = await res.json();
    const select = document.getElementById("inventoryWarehouseSelect");
    select.innerHTML = `<option value="">All Warehouses</option>` + warehouses.map(w => `<option value="${w.id}">${w.name}</option>`).join("");
    loadInventory();
    select.onchange = loadInventory;

    document.getElementById("addInventoryBtn").onclick = () => openInventoryModal();
    document.getElementById("inventorySearch").oninput = (e) => filterTable(e.target.value, document.querySelector("#inventoryTable tbody"));
}

async function loadInventory() {
    const warehouseId = document.getElementById("inventoryWarehouseSelect").value;
    const url = warehouseId ? `/Products/warehouse/${warehouseId}` : `/Products`;
    const res = await fetch(url);
    const products = await res.json();
    const tbody = document.querySelector("#inventoryTable tbody");

    tbody.innerHTML = products.map(p => `
        <tr>
            <td>${p.name || "N/A"}</td>
            <td>${p.sku || "N/A"}</td>
            <td>${p.description || "N/A"}</td>
            <td>${p.quantity || 0}</td>
            <td>${p.location || "N/A"}</td>
            <td>${p.warehouse?.name || "N/A"}</td>
            <td>
                <button class="edit" onclick="editInventory(${p.id})">Edit</button>
                <button class="delete" onclick="deleteInventory(${p.id})">Delete</button>
            </td>
        </tr>
    `).join("");
}

function openInventoryModal(product = {}) {
    const modal = document.getElementById("modal");
    const modalBody = document.getElementById("modalBody");

    modalBody.innerHTML = `
        <h3>${product.id ? "Edit" : "Add"} Inventory</h3>
        <form id="inventoryForm">
            <label>Name</label>
            <input type="text" name="name" value="${product.name || ""}" required>
            <label>SKU</label>
            <input type="text" name="sku" value="${product.sku || ""}" required>
            <label>Description</label>
            <input type="text" name="description" value="${product.description || ""}" required>
            <label>Quantity</label>
            <input type="number" name="quantity" value="${product.quantity || 0}" required>
            <label>Location</label>
            <input type="text" name="location" value="${product.location || ""}" required>
            <label>Warehouse</label>
            <select name="warehouseId" required>
                ${Array.from(document.getElementById("inventoryWarehouseSelect").options).map(o => `<option value="${o.value}" ${o.value == (product.warehouse?.id || "") ? "selected" : ""}>${o.text}</option>`).join("")}
            </select>
            <button type="submit">${product.id ? "Update" : "Create"}</button>
        </form>
    `;

    document.getElementById("inventoryForm").onsubmit = async (e) => {
        e.preventDefault();
        const formData = Object.fromEntries(new FormData(e.target).entries());
        formData.quantity = parseInt(formData.quantity);

        if (product.id) {
            await fetch(`/Products/${product.id}`, { method: "PUT", headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(formData) });
        } else {
            await fetch(`/Products`, { method: "POST", headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(formData) });
        }

        modal.style.display = "none";
        loadInventory();
    };

    openModal();
}

async function editInventory(id) {
    const res = await fetch(`/Products/${id}`);
    const product = await res.json();
    openInventoryModal(product);
}

async function deleteInventory(id) {
    if (confirm("Are you sure you want to delete this inventory item?")) {
        await fetch(`/Products/${id}`, { method: "DELETE" });
        loadInventory();
    }
}

/* ======================== MODAL UTILS ======================== */
function openModal() {
    const modal = document.getElementById("modal");
    modal.style.display = "block";

    modal.querySelector(".close").onclick = () => { modal.style.display = "none"; };
    window.onclick = (event) => { if (event.target == modal) modal.style.display = "none"; };
}
