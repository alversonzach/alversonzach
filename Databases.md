# Databases

## Naritive

### Briefly describe the artifact. What is it? When was it created?

This artifact is a web application using node.js, express, JavaScript, and MongoDB. It is a simple pet database that allows the user to input a name, breed, age, and owner. It is then added to a MongoDB database. It can be updated and deleted via the web app. It was created during this course.

### Justify the inclusion of the artifact in your ePortfolio. Why did you select this item? What specific components of the artifact showcase your skills and abilities in software development? How was the artifact improved?

I wanted to include this artifact because it shows my proficiency with using databases. Instead of using a simple SQL databases with commands to create, read, update, and delete I wanted to try something a little more out of my comfort zone and create a web application using node.js, express, and JavaScript to make it more interactive for a user. The abilities of software development that this showcases is being able to make an entire application that connects to a database.

## Files created for the web application

### server.js

```
require('./models/db');

// Request statement for express
const express = require('express');
const path = require('path');
const exphbs = require('express-handlebars');
const hbs = require('express-handlebars');


const petController = require('./controllers/petController');

var app = express();
app.use(express.urlencoded({
    extended: true
}));
app.use(express.json());
app.set('views', path.join(__dirname, '/views/'));
app.engine('handlebars', exphbs.engine({ extname: 'handlebars', defaultLayout:'mainLayout', layoutsDir: __dirname + '/views/layouts/' /*hanldlebars: allowInsecurePrototypeAccess(Handlebars)*/ }));
app.set('view engine','handlebars');

// Listens for callback from port 3000
app.listen(3000, () => {
    console.log('Express server started at port : 3000');
});

app.use('/pet', petController);
```

### db.js

```
// Request statement for mongoose
const mongoose = require('mongoose');

// Function to connect MongoDB with port and data file
mongoose.connect('mongodb://localhost:27017/PetDB', { useNewUrlParser: true }, (err) => {
    if (!err) { console.log('MongoDB Connection Succeeded.') }
    else { console.log('Error in DB connection : ' + err) }
});

// Request the pet model 
require('./pet.model');
```

### pet.models.js

```
// Request statement for mongoose
const mongoose = require('mongoose');

// Object and constructor for petSchema
var petSchema = new mongoose.Schema({
    // Fields for the document petSchema 
    petName: {
        type: String
    },
    breed: {
        type: String
    },
    age: {
        type: String
    },
    owner: {
        type: String
    }
});

// Places petSchema inside mongoose
mongoose.model('Pet', petSchema);
```

### petController.js

```
const express = require('express');
var router = express.Router();
const mongoose = require('mongoose');
const Pet = mongoose.model('Pet');

// Connect with router to request and respond for insert
router.get('/', (req, res) => {
    res.render("pet/addOrEdit", {
        viewTitle: "Insert Pet"
    });
});

// Connect with router to request and respond for update
router.post('/', (req, res) => {
    if (req.body._id == '')
        insertRecord(req, res);
        else
        updateRecord(req, res);
});

// Crud operations to be preformed
function insertRecord(req, res) {
    var pet = new Pet();
    pet.petName = req.body.petName;
    pet.breed = req.body.breed;
    pet.age = req.body.age;
    pet.owner = req.body.owner;
    pet.save((err, doc) => {
        if (!err)
            res.redirect('pet/list');
        else {
            if (err.name == 'ValidationError') {
                handleValidationError(err, req.body);
                res.render("pet/addOrEdit", {
                    viewTitle: "Insert Pet",
                    pet: req.body
                });
            }
            else
                console.log('Error during record insertion : ' + err);
        }
    });
}

function updateRecord(req, res) {
    Pet.findOneAndUpdate({ _id: req.body._id }, req.body, { new: true }, (err, doc) => {
        if (!err) { res.redirect('pet/list'); }
        else {
            if (err.name == 'ValidationError') {
                handleValidationError(err, req.body);
                res.render("pet/addOrEdit", {
                    viewTitle: 'Update Pet',
                    employee: req.body
                });
            }
            else
                console.log('Error during record update : ' + err);
        }
    });
}


// Connect with router to request and respond to add to list
router.get('/list', (req, res) => {
    Pet.find((err, docs) => {
        if (!err) {
            res.render("pet/list", {
                list: docs
            });
        }
        else {
            console.log('Error in retrieving pet list :' + err);
        }
    });
});


function handleValidationError(err, body) {
    for (field in err.errors) {
        switch (err.errors[field].path) {
            case 'petName':
                body['petNameError'] = err.errors[field].message;
                break;
            case 'breed':
                body['breedError'] = err.errors[field].message;
                break;
            default:
                break;
        }
    }
}

router.get('/:id', (req, res) => {
    Pet.findById(req.params.id, (err, doc) => {
        if (!err) {
            res.render("pet/addOrEdit", {
                viewTitle: "Update Pet",
                pet: doc
            });
        }
    });
});

// Connect with router with request and respond to delete
router.get('/delete/:id', (req, res) => {
    Pet.findByIdAndRemove(req.params.id, (err, doc) => {
        if (!err) {
            res.redirect('/pet/list');
        }
        else { console.log('Error in pet delete :' + err); }
    });
});

module.exports = router;
```

### mainLayout.handlebars

```
<!-- This is the HTML for the main layout of the application -->
<!DOCTYPE html>

<html>

<head>
    <title>Node.js express mongDB CRUD</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body class="bg-info">
    <div class="row">
        <div class="col-md-6 offset-md-3" style="background-color: #fff;margin-top: 25px;padding:20px;">
            {{{body}}}
        </div>
    </div>

</body>

</html>
```

### addOrEdit.handlebars

```
<!-- This is the HTML for the the CRUD operations on the main page of the application -->
<h3>{{viewTitle}}</h3>

<form action="/pet" method="POST" autocomplete="off">
    <input type="hidden" name="_id" value="{{pet._id}}">
    <div class="form-group">
        <label>Pet Name</label>
        <input type="text" class="form-control" name="petName" placeholder="Pet Name" value="{{pet.petName}}">
        <div class="text-danger">
            {{pet.petNameError}}</div>
    </div>
    <div class="form-group">
        <label>Breed</label>
        <input type="text" class="form-control" name="breed" placeholder="Breed" value="{{pet.breed}}">
        <div class="text-danger">
            {{pet.breedError}}</div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label>Age</label>
            <input type="text" class="form-control" name="age" placeholder="Age" value="{{pet.age}}">
        </div>
        <div class="form-group col-md-6">
            <label>Owner</label>
            <input type="text" class="form-control" name="owner" placeholder="Owner" value="{{pet.owner}}">
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-info"><i class="fa fa-database"></i> Submit</button>
        <a class="btn btn-secondary" href="/pet/list"><i class="fa fa-list-alt"></i> View All</a>
    </div>
</form>
```

### list.handlebars

```
<!-- This is the HTML for the List section of the application -->
<h3><a class="btn btn-secondary" href="/pet"><i class="fa fa-plus"></i> Create New</a> Pet List</h3>
<table class="table table-striped">
    <thead>
        <tr>
            <th>Pet Name</th>
            <th>Breed</th>
            <th>Age</th>
            <th>Owner</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        {{#each list}}
        <tr>
            <td>{{this.petName}}</td>
            <td>{{this.breed}}</td>
            <td>{{this.age}}</td>
            <td>{{this.owner}}</td>
            <td>
                <a href="/pet/{{this._id}}"><i class="fa fa-pencil fa-lg" aria-hidden="true"></i></a>
                <a href="/pet/delete/{{this._id}}" onclick="return confirm('Are you sure to delete this record ?');"><i class="fa fa-trash fa-lg" aria-hidden="true"></i></a>
            </td>
        </tr>
        {{/each}}
    </tbody>
</table>
```

### Run batch file

cd C:\Program Files\MongoDB\Server\5.0\bin
mongod.exe --dbpath C:\Users\zacha\mongo-data

### Open MongoDBCompass and create pets collection
![Screenshot (22)](https://user-images.githubusercontent.com/97413992/154858072-e893dbdd-a6cc-4c70-8fde-5664d9036b8c.png)

### Start the server in code editor using nodemon server.js
![Screenshot (23)](https://user-images.githubusercontent.com/97413992/154858208-58ac7774-3c41-4849-8fd1-94b2d087e235.png)

### In web browser open localhost:3000/pet
![Screenshot (24)](https://user-images.githubusercontent.com/97413992/154858290-6fa9b42a-4509-4e6d-b0cd-862e72a846de.png)

### Fill out required fields
![Screenshot (25)](https://user-images.githubusercontent.com/97413992/154858331-08c6f7da-e10e-4420-acc7-c5fc23373bfd.png)

### It will then be added to the MongoDB database
![Screenshot (26)](https://user-images.githubusercontent.com/97413992/154858376-3ab6faeb-d1e5-4720-9132-f62b24df82f6.png)

### Reflect on the process of enhancing and/or modifying the artifact. What did you learn as you were creating it and improving it? What challenges did you face?

This was a fun project to complete. I was familiar with MongoDB but not node.js, express, or JavaScript. For the most part I felt comfortable working with everything. Learning JavaScript was a bit difficult, but I felt that I overcame this to make it work. Something that could be added would be a security feature with a login and a password. 





