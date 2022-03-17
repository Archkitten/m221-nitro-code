// Definitions
let taskContainer = document.getElementById("task-list");
let tPopup = document.getElementById("popup-template");
let dayNames = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
let currentDate = 0;
let totalDates = 7;
let taskCards = [];
let dataStorage = {};
const pageContainer = document.getElementById("page-container");

function generateCards(rows, column) {
    for(let i = 0; i < rows; i++) {
        taskCards[i] = [];
        let cardContainer = document.createElement("div");
        cardContainer.className = "card-container";
        let today = new Date();

        for(let j = 0; j < column; j++) {
            let taskCard = document.createElement("div");
            taskCard.className = "task-card";
            taskCards[i][j] = taskCard;
            cardContainer.appendChild(taskCard);
            let dateCard = document.createElement("div");
            dateCard.className = "date-card";
            dateCard.innerText = today.addDays(j).toString().split(" ")[2];
            taskCard.appendChild(dateCard);
        }
        taskContainer.appendChild(cardContainer);
    }
    if (localStorage.getItem("tasks") != null) {
        dataStorage = JSON.parse(localStorage.getItem("tasks"));
        renderCardsContent();
    }
}

function newTask() {
    pageContainer.appendChild(tPopup.content.firstElementChild.cloneNode(true));
}

function cancel(self) {
    self.parentElement.parentElement.remove();
}

function addTask() {
    let formTitle = document.getElementById("TTitle").value;
    let formContent = document.getElementById("TContents").value;
    let [year, month, day] = document.getElementById("TDate").value.split("-");
    if(formTitle.length == 0) return; //maybe add error message to tell the user that he/she can't leave title empty
    let formDate = new Date(year, month-1, day);
    let unixTime = formDate.getTime();

    // weekly handler
    if(dataStorage[unixTime] === undefined) dataStorage[unixTime] = [];
    dataStorage[unixTime].push( {
        "title": formTitle,
        "content": formContent
    } );
    localStorage.setItem("tasks", JSON.stringify(dataStorage));

    renderCardsContent();
    postTasks();
}

function renderCardsContent() {
    let date = new Date()
    for(let i = currentDate; i < totalDates; i++) {
        // adding i day(s) to user's current time
        let dateIndex = (new Date(date.getFullYear(), date.getMonth(), date.getDate())).setDate(date.getDate() + i);
        let data = dataStorage[dateIndex];
        let currentCard = taskCards[Math.floor(i/7)][i % 7];
        // clear contents in current card, add date
        currentCard.innerHTML = '';
        var today = new Date();
        let dateCard = document.createElement("div");
        dateCard.className = "date-card";
        dateCard.innerText = today.addDays(i).toString().split(" ")[2];
        currentCard.appendChild(dateCard);

        if (data !== undefined) {
            data.forEach(date => {
                var subCard = document.createElement("details");
                subCard.className = "sub-card";
                subCard.ondblclick = function(){
                    displayTask(date["title"], date["content"], today.addDays(i));
                };
                currentCard.appendChild(subCard);
                subCard.innerHTML = "<summary>" + date["title"] + "</summary>" + date["content"];
            });
        }
    }
}

function displayTask(ti, co, da) {
    $("#taskDisplay").modal();
    document.getElementById("taskName").setAttribute("value", ti);
    document.getElementById("taskContents").innerText = co;
    document.getElementById("taskDate").valueAsDate = da;
}

// this function is for testing purposes only. you might want to delete it for the final release.
function clearTasks() {
    localStorage.removeItem("tasks");
    dataStorage = {};
    document.querySelectorAll('.sub-card').forEach(e => e.remove());
}

Date.prototype.addDays = function(days) {
    var date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
}

async function postTasks() {
    let form = document.getElementById("todoForm");
    let data = await (await fetch('/todoPost', {
        method: 'POST',
        body: new FormData(form)
    })).text();
}

