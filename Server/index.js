/** Didn't succeed completing the firebase actions
 * Only through their website interface 
*/

// Const setup
const http = require('http')
const alpha = require('alphavantage')({ key: 'OZI8A8JY0R5NHONI' });
const app = require('express')();
const server = require('http').createServer(app);
const io = require('socket.io')(server);

// Setup Firebase cloud messaging
const FCM = require('fcm-push');
const serverKey = 'AAAANh_BDdk:APA91bExnLhxv2ZYGBwmeJsWZIj7uxnJmLFp0tnATwnDZSbib2OzKvw2Sh53-fUVnvNkNpEhpqnPcJineu3UznRTwKXRj3-p_t0Pl6GEPQb77IU2pR8NYaBu9TffdqNCaSmTGMQBp6_S';
const fcm = new FCM(serverKey);

// variables setup
let stock = '';
let stockPrice = '';
let price = '';
let tokens = {};

// handle tokens
app.post('/:user/token', (req, res, next) => {
    let token = req.body.myToken;
    // Monitor that we got the token fro the user
    console.log(`Received save token request from ${req.params.user} for token=${token}`);
    if (!token) return res.status(400).json({err: "missing token"});
    tokens[req.params.user] = token;
    res.status(200).json({msg: "saved ok"});
});

// Setup connection event for socket.io
io.on('connection', (socket) => {
    
    console.log(tokens)
    console.log('one user connected ' + socket.id);

    // FCM message setup
    let fcm_message = {
        to: tokens['nadavlotan'],
        data: {
            someKey: 'Stock Price'
        },
        notification: {
            title: 'Stock Price',
            body: 'Body of stock price'
        }
    }

    fcm.send(fcm_message, (err, response) => {
        if (err) {
            console.log("Couldn't connect to firebase");
        } else {
            console.log("Successfully send with response: ", response);
        }
    })

    // Handle message event from client side
    socket.on('message', (data) => {
        console.log(data);
        stock = data['stock'];

        alpha.data.batch([stock]).then(data => {
            price = `${data['Stock Quotes'][0][`2. price`]}`;
        });
        socket.emit('message', price);

        setInterval(() => {
            alpha.data.batch([stock]).then(data => {
                stockPrice = `${stock} -> ${data['Stock Quotes'][0][`2. price`]}`;
                console.log(stockPrice);
                price = `${data['Stock Quotes'][0][`2. price`]}`;
            })
            .catch(err => {
                console.error("Error: " + err);
            });
            console.log(stockPrice);
            console.log(price);
            socket.emit('message', price);
        }, 40000);
    });

    socket.on('disconnect', () => {
        console.log('one user disconnected ' + socket.id);
    })
    });

server.listen(8080, ()=>{
    console.log('Server listening on port 8080')
});