const express = require('express')
const app = express()
var path = require('path');


app.use(express.static(path.join(__dirname, 'public')));


app.get('/', function (req, res) {

   console.log("Hello World!")
  res.send('Hello World!')
})

app.get('/hitserver', function (req, res) {

   console.log("hit the server!")
  res.send('Hello World!')
})


app.listen(3000, function () {
  console.log('Example app listening on port 3000!')
})