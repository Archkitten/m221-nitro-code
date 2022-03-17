let note = document.getElementById("note-template");
const notes = document.getElementsByClassName("note")
let noteContainer = document.getElementById("nc-template");
var nCs = document.getElementsByClassName("noteContainer");
var nCTs = document.getElementsByClassName("noteContainerTitle");
const colContainer = document.getElementById("colContainer");

function addNote(self) {
    self.parentElement.parentElement.children[1].appendChild(note.content.firstElementChild.cloneNode(true));
}

function deleteNote(self) {
    self.parentElement.parentElement.parentElement.remove();
}

function addColumn() {
    colContainer.appendChild(noteContainer.content.firstElementChild.cloneNode(true))
    adjustColSize();
}

function deleteColumn(self) {
    self.parentElement.parentElement.remove();
    adjustColSize();
}

function adjustColSize() {
    if (nCs.length<=4) {
        for(var ii = 0; ii < nCs.length; ii++){
            nCs[ii].style.width = "" + (100/nCs.length)-2 + "%";
        }
        for(var ii = 0; ii < notes.length; ii++){
            notes[ii].style.width = "" + noteContainer.style.width -1 + "%";
        }
    }
    if (nCs.length>4) {
        for(var ii = 0; ii < nCs.length; ii++){
            nCs[ii].style.width = "" + 23 + "%";
        }
    }
    for(var ii = 0; ii < nCTs.length; ii++){
        nCTs[ii].style.width = nCs[0].clientWidth - 120 + "px";
    }
}