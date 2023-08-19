function deleteRoute(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "put"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Something wrong!!!");
        });
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

function logout(){
    localStorage.removeItem("token");
}