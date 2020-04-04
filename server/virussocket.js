const mysql      = require('mysql');
const http = require('http');

const pool = mysql.createPool({
    host     : 'powermail.icu',
    user     : 'virus',
    password : 'teamswitch',
    database : 'versusvirus'
});





const server = http.createServer(null);
const io = require('socket.io')(server);

server.listen(8000);
io.on('connection', (socket)=>{
    console.log("conn")
    socket.on('newStudent', (prename, surname, id, cb)=>{
        pool.query('INSERT INTO students (prename, surname, class_id) VALUES (?,?,?);', [prename, surname, id], (error, result)=>{
            if (error) console.log(error)
            cb();
        })
    })
    socket.on('newTask', (title, description, course_id, dueDate, student_id, cb)=>{
        pool.query('INSERT INTO tasks (title, description, course_id, dueDate, student_id) VALUES (?,?,?,?,?);', [title, description, course_id, dueDate, student_id], (error, result)=>{
            console.log("done")
            if (error) console.log(error)
            cb();
        })
    })
    socket.on('newTeacher', (prename, surname, cb)=>{
        pool.query('INSERT INTO teachers (prename, surname) VALUES (?,?);', [prename, surname], (error, result)=>{
            if (error) console.log(error)
            cb();
        })
    })
})
