// Store current user data
let currentUser = {
    type: null,
    username: null
};

// Login function
function login(userType) {
    const username = document.querySelector('input[type="text"]').value;
    const password = document.querySelector('input[type="password"]').value;

    if (username && password) {
        // Store user info
        currentUser.type = userType;
        currentUser.username = username;

        // Redirect to appropriate dashboard
        if (userType === 'user') {
            window.location.href = 'user-dashboard.html';
        } else if (userType === 'volunteer') {
            window.location.href = 'volunteer-dashboard.html';
        } else if (userType === 'ngo') {
            window.location.href = 'ngo-dashboard.html';
        }
    } else {
        alert('Please fill in all fields');
    }
}

// Logout function
function logout() {
    if (confirm('Are you sure you want to logout?')) {
        currentUser = {
            type: null,
            username: null
        };
        window.location.href = 'index.html';
    }
}

// Initialize dashboard on page load
function initDashboard() {
    const welcomeMsg = document.querySelector('.dashboard-header h2');
    if (welcomeMsg && currentUser.username) {
        welcomeMsg.textContent = `Welcome, ${currentUser.username}`;
    }
}

// Sample data for tables
const disastersData = [
    { name: 'Flood - Maharashtra', location: 'Mumbai, Maharashtra', date: '2025-09-15', status: 'Active' },
    { name: 'Earthquake - Gujarat', location: 'Ahmedabad, Gujarat', date: '2025-09-10', status: 'Pending' },
    { name: 'Cyclone - Odisha', location: 'Bhubaneswar, Odisha', date: '2025-09-05', status: 'Completed' }
];

const resourcesData = [
    { name: 'Food Packets', type: 'Food & Water', location: 'Camp A - Mumbai', status: 'Available' },
    { name: 'Medical Supplies', type: 'Healthcare', location: 'Camp B - Ahmedabad', status: 'Available' },
    { name: 'Tents & Shelter', type: 'Shelter', location: 'Camp C - Bhubaneswar', status: 'Low Stock' }
];

const volunteersData = [
    { name: 'Rajesh Kumar', availability: 'Full Time', location: 'Mumbai', status: 'Active' },
    { name: 'Priya Sharma', availability: 'Part Time', location: 'Ahmedabad', status: 'Active' },
    { name: 'Amit Patel', availability: 'Weekends', location: 'Pune', status: 'Active' }
];

const campsData = [
    { name: 'Relief Camp Alpha', location: 'Mumbai Central', disaster: 'Flood', capacity: '500/800', status: 'Active' },
    { name: 'Relief Camp Beta', location: 'Ahmedabad North', disaster: 'Earthquake', capacity: '300/500', status: 'Active' },
    { name: 'Relief Camp Gamma', location: 'Bhubaneswar East', disaster: 'Cyclone', capacity: '200/400', status: 'Closed' }
];

// Load table data
function loadTableData(tableId, data) {
    const tbody = document.querySelector(`#${tableId} tbody`);
    if (tbody) {
        tbody.innerHTML = '';
        data.forEach(row => {
            const tr = document.createElement('tr');
            Object.values(row).forEach(value => {
                const td = document.createElement('td');
                if (value === 'Active') {
                    td.innerHTML = `<span class="badge badge-active">${value}</span>`;
                } else if (value === 'Pending' || value === 'Low Stock') {
                    td.innerHTML = `<span class="badge badge-pending">${value}</span>`;
                } else if (value === 'Completed' || value === 'Closed') {
                    td.innerHTML = `<span class="badge badge-completed">${value}</span>`;
                } else if (value === 'Available') {
                    td.innerHTML = `<span class="badge badge-active">${value}</span>`;
                } else {
                    td.textContent = value;
                }
                tr.appendChild(td);
            });
            tbody.appendChild(tr);
        });
    }
}

// Initialize page
document.addEventListener('DOMContentLoaded', function() {
    initDashboard();
});

// Apply for operation (volunteer dashboard)
function applyForOperation(operationName) {
    if (confirm(`Do you want to apply for ${operationName}?`)) {
        alert('Application submitted successfully! You will be notified once approved.');
    }
}

// Add new camp (NGO dashboard)
function addCamp() {
    alert('Add New Camp form would open here');
}

// Request help (User dashboard)
function requestHelp() {
    alert('Request Help form would open here');
}
// ____________________________________________________________________________________________________________________________________



