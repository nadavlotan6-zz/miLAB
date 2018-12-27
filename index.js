const express = require('express');
const fs = require('fs')
const app = express();
const path = require('path')

/** 
 * Sets the get response to a certain image,
 * if exist, return the image as a stream
 * if doesn't exist, return the 'error.html' page
 */
app.get('/files/:filename', (req, res) => {
    let filename = req.params['filename']
    fs.exists(`./files/${filename}`, (exists) => {
        if (exists) {
        let readStream = fs.createReadStream(`./files/${filename}`);
        readStream.pipe(res);
        } else {
            // use path to the 'error.html' file to present it
            res.sendFile(path.join(__dirname + '/files/error.html'));
        }
    });    
});

app.get('*', (req, res) => {  
    res.sendFile(path.join(__dirname, '/files/error.html'));
});


/**
 * Start the server:
 * Listens on port 3000
 */
app.listen(3000, () => console.log(`Server is running on port 3000!`));