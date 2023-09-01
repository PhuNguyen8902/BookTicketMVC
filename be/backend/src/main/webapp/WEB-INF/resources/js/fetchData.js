async function getApi(api) {
    const response = await fetch(api, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (!response.ok) {
        throw new Error("Network response was not ok");
    }

    return response.json();
}
async function putApi(api) {
    const response = await fetch(api, {
        method: "PUT",
    });

    if (response.status === 204)
        location.reload();
    else
        alert("Something wrong!!!");
}

async function deleteRoute(path, id) {
    const api = "http://localhost:8080/backend/api/trip";
    const data = await getApi(api);
    
    const foundRouteInTrip = data.some(item => item.routeId == id);
    if (foundRouteInTrip) {
        alert("There are routes still working in Trip");
        return;
    }

    if (confirm("Bạn chắc chắn xóa không?") === true) {
        putApi(path)
    }
}
function deleteTrip(path, id) {

    if (confirm("Bạn chắc chắn xóa không?")) {
        putApi(path);
    }
}
async function deleteStation(path, name){
    const api = "http://localhost:8080/backend/api/route";
    const data = await getApi(api);
    console.log(data);
    const foundStationInRoute = data.some(item => {
        return item.startStation == name || item.endStation == name
    });

    if (foundStationInRoute) {
        alert("There are stations still working in route");
        return;
    }
    
    if (confirm("Bạn chắc chắn xóa không?")) {
        putApi(path);
    }
}

async function deleteAddress(path, id){
    const api = "http://localhost:8080/backend/api/station";
    const data = await getApi(api);
    
    const fromAddressInStation = data.some(item => item.addressId == id);
    
    if (fromAddressInStation) {
        alert("There are address still working in station");
        return;
    }
    
    if (confirm("Bạn chắc chắn xóa không?")) {
        putApi(path);
    }
}

async function deleteVehicle(path, id) {
    const api = "http://localhost:8080/backend/api/trip";
    const data = await getApi(api);
//    console.log(data);
    const foundVehicleInTrip = data.some(item => item.vehicleId == id);

    if (foundVehicleInTrip) {
        alert("There are vehicles still working in Trip");
        return;
    }

    if (confirm("Bạn chắc chắn xóa không?")) {
        putApi(path);
    }
}


function login(form) {
    event.preventDefault();
    let emailValue = form.email.value;
    let passwordValue = form.password.value;
    let requestData = {
        email: emailValue,
        password: passwordValue
    };
    let api = "http://localhost:8080/backend/api/auth/authenticate/";
    fetch(api, {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(requestData)
    })
            .then((res) => res.json())
            .then((data) => {
                console.log(data);
                localStorage.setItem("token", JSON.stringify(data));
            })
            .catch((error) => {
                console.error("Error:", error);
            });
}

function fetchInfo() {
    let api = "http://localhost:8080/backend/api/employee/accessToken/";
    const token = JSON.parse(localStorage.getItem("token"));

    fetch(api, {
        method: "get",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token.accessToken}`
        }
    })
            .then(response => response.json()) // Chuyển response thành JSON
            .then(data => {
                console.log(data); // Log dữ liệu phản hồi
                displayData(data);
            })
            .catch(error => {
                console.error("Error:", error);
            });
}
function displayData(data) {
    const elements = document.querySelectorAll("#fetchName");
    elements.forEach(element => {
        element.textContent = data.name;
    });
}

function logout() {
    localStorage.removeItem("token");
}
