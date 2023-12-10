"use strict"

const csrfToken = document.getElementById("csrfToken").value
const validateRoute = document.getElementById("validateRoute").value
const createUserRoute = document.getElementById("createUserRoute").value
//const logoutRoute = document.getElementById("logoutRoute").value
const openCategoryRoute = document.getElementById("openCategoryRoute").value
const scheduleRoute = document.getElementById("scheduleRoute").value

function login() {
    const username = document.getElementById("username-login").value;
    const password = document.getElementById("password-login").value;
    console.log("login() in js");
    fetch(validateRoute, {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Csrf-Token': csrfToken},
        body: JSON.stringify({ username, password })
    }).then(res => res.json()).then(data => {
        if(data) {
            document.getElementById("login-section").hidden = true;
            document.getElementById("create-user-section").hidden = true;
            document.getElementById('home-page').style.display = 'flex';
            document.getElementById("logout").hidden = false;
            document.getElementsByTagName("TITLE")[0].text="Resources";
        } else {
            console.log("failed validate");
        }
    })
}
function createUser() {
    const username = document.getElementById("username-create").value;
    const password = document.getElementById("password-create").value;
    console.log("createUser() in js");
    fetch(createUserRoute, {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Csrf-Token': csrfToken},
        body: JSON.stringify({ username, password })
    }).then(res => res.json()).then(data => {
        if(data) {
            document.getElementById("login-section").hidden = true;
            document.getElementById("create-user-section").hidden = true;
            document.getElementById('home-page').style.display = 'flex';
            document.getElementById("logout").hidden = false;
            document.getElementsByTagName("TITLE")[0].text="Resources";
        } else {
            console.log("failed to create");
        }
    })
}
function showHomePage() {

    document.getElementsByTagName("TITLE")[0].text="Resources";
    document.getElementById('login-section').style.display = 'none';
    document.getElementById('create-user-section').style.display = 'none';
    document.getElementById('home-page').style.display = 'flex';
}


function openCategory(category) {
    console.log("openCategory in js");
    hideHomePage()
    const resourceTable = document.getElementById("resource-table")
    resourceTable.innerHTML = ""

    const header = document.getElementById("currentResource")
    header.innerHTML = ""
    const categoryName = document.createTextNode(category)
    header.appendChild(categoryName)

    fetch(openCategoryRoute, {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Csrf-Token': csrfToken},
        body: JSON.stringify(category)
    }).then(res => res.json()).then(resources => {
        //TODO: Make button to "schedule" each resource
        if (resources) {
            document.getElementsByTagName("TITLE")[0].text=category;
            
            console.log(resources);
            document.getElementById("home-page").hidden = true
            document.getElementById("resource-page").hidden = false
            
            let trSourceHeader = document.createElement("tr")
            let sourceHeaders = ["Resouce", "Description", "Hours"]

            for(const h of sourceHeaders) {
                let txtNode = document.createTextNode(h)
                const cell = document.createElement("th")
                cell.appendChild(txtNode)
                trSourceHeader.appendChild(cell)
            }

            resourceTable.appendChild(trSourceHeader)

            for(const resource of resources) {
                //console.log("here is a resource");
                console.log(resource);
                let tr = document.createElement("tr")
    
                let resourceName = document.createTextNode(resource.name)
                const resourceNameCell = document.createElement("td")
                resourceNameCell.appendChild(resourceName)
                tr.appendChild(resourceNameCell)
    
                let description = document.createTextNode(resource.description)
                let descriptionCell = document.createElement("td")
                descriptionCell.appendChild(description)
                tr.appendChild(descriptionCell)
    
                let hours = document.createTextNode(resource.hours)
                let hoursCell = document.createElement("td")
                hoursCell.appendChild(hours)
                tr.appendChild(hoursCell)

                if(resource.schedule) {
                    console.log("is there a button?");
                    const scheduleBtn = document.createElement("button")
                    const btnName = document.createTextNode(resource.name)
                    scheduleBtn.appendChild(btnName)
                    scheduleBtn.setAttribute("onclick", `schedule('${category}','${resource.name}')`)
                    tr.appendChild(scheduleBtn)
                }
    
                resourceTable.appendChild(tr)
            }
        } else {
            console.log("failed to fetch resources")
        }
    })
}

function back() {
    
    document.getElementsByTagName("TITLE")[0].text="Resources";
    document.getElementById("home-page").style.display = 'flex'
    document.getElementById("resource-page").hidden = true
}

function hideHomePage() {
    document.getElementById("home-page").style.display = 'none'
    document.getElementById("resource-page").hidden = false
    
}

function schedule(category, resourceName) {
    console.log(resourceName)
    document.getElementById("home-page").hidden = true
    document.getElementById("resource-page").hidden = true
    document.getElementById("schedule-page").hidden = false

    fetch(scheduleRoute, {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Csrf-Token': csrfToken},
        body: JSON.stringify({category, resourceName})
    }).then(res => res.json()).then(resource => {
        if(resource) {
            console.log("fetching" + resource)
        } else {
            console.log("failed schedule fetch")
        }
    })

}



