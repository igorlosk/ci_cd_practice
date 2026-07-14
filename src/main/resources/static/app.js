async function createPhone() {

    const phone = {
        brandName: document.getElementById("brand").value,
        model: document.getElementById("model").value,
        price: Number(document.getElementById("price").value)
    };

    const response = await fetch("/api/phone", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(phone)
    });

    const data = await response.json();

    document.getElementById("result").textContent =
        JSON.stringify(data, null, 2);
}

async function getPhone() {

    const id = document.getElementById("phoneId").value;

    const response = await fetch("/api/phone/" + id);

    const data = await response.json();

    document.getElementById("result").textContent =
        JSON.stringify(data, null, 2);
}