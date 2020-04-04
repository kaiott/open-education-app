var ce= require("../name.js")
var ioClient = require('socket.io-client')
var socket = ioClient('http://localhost:8000');

let text = `Lorem ipsum dolor sit amet, consectetuer adipiscing elit.

Aenean commodo ligula eget dolor.

Aenean massa.

Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.

Donec qu`



function newStudent(prename, surname){
    let classes = [1,2,3,4,5,6,7,8,9,10]
    socket.emit('newStudent', prename, surname, Math.floor(Math.random()*classes.length)+1, ()=> {
        console.log(prename)
    })   
}
function newTask(title, description, dueDate){
    let classes = [1,2,3,4,5,6,7,8,9,10]
    socket.emit('newTask', title, description, Math.floor(Math.random()*classes.length)+1, dueDate, Math.floor(Math.random()*190),()=> {
        console.log(title)
    })   
}


function newTeacher(prename, surname){
    socket.emit('newTeacher', prename, surname, ()=> {
        console.log(prename)
    })   
}

socket.on('connect', ()=>{
    for(let i = 1; i <2; i++){
        let creds = ce.CE()
        let surn = creds.surname
        let pname = creds.prename
        //newStudent(pname, surn)
        //newTask("test"+i, text, 1586034809+i*600)
        newTeacher(pname, surn)
    }
})