const API="/api/phone";

loadPhones();

function loadPhones(){

    fetch(API)
        .then(r=>r.json())
        .then(data=>{

            const table=document.getElementById("phonesTable");

            table.innerHTML="";

            data.forEach(phone=>{

                table.innerHTML+=`
                <tr>
                    <td>${phone.id}</td>
                    <td>${phone.brandName}</td>
                    <td>${phone.model}</td>
                    <td>${phone.price}</td>

                    <td>

                        <button
                            class="btn btn-warning btn-sm"
                            onclick="editPhone(${phone.id})">
                            Edit
                        </button>

                        <button
                            class="btn btn-danger btn-sm"
                            onclick="deletePhone(${phone.id})">
                            Delete
                        </button>

                    </td>

                </tr>
                `;

            });

        });

}

function savePhone(){

    const id=document.getElementById("phoneId").value;

    const phone={

        brandName:document.getElementById("brand").value,
        model:document.getElementById("model").value,
        price:Number(document.getElementById("price").value)

    };

    if(id===""){

        fetch(API,{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(phone)
        })
            .then(loadPhones)
            .then(clearForm);

    }else{

        fetch(API+"/"+id,{
            method:"PUT",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(phone)
        })
            .then(loadPhones)
            .then(clearForm);

    }

}

function deletePhone(id){

    if(!confirm("Delete phone?"))
        return;

    fetch(API+"/"+id,{
        method:"DELETE"
    })
        .then(loadPhones);

}

function editPhone(id){

    fetch(API+"/"+id)
        .then(r=>r.json())
        .then(phone=>{

            document.getElementById("phoneId").value=phone.id;
            document.getElementById("brand").value=phone.brandName;
            document.getElementById("model").value=phone.model;
            document.getElementById("price").value=phone.price;

        });

}

function clearForm(){

    document.getElementById("phoneId").value="";
    document.getElementById("brand").value="";
    document.getElementById("model").value="";
    document.getElementById("price").value="";

}