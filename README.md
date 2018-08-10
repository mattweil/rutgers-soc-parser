<h1>Rutgers SOC Parser</h1>

Rutgers SOC Parser is a command line project written in Java that allows users to pull large amounts of data from Rutger's SOC API and populate databases accordingly.

<h2>How to use</h2>

Before running the program, it is required that a MongoDB instance is running locally for so there is an endpoint for information. If the parser fails to connect to a database an error will be thrown.

| Command | Description 
| --- | --- 
| `rucore` | Extracts and parses course data into a MongoDB Model
| `ruclass` | Extracts and parses class meeting time data into a MongoDB Model

<h2>Models</h2>

These models are created by the parser and are automatically inserted once parsed.

<h4>RU Core MongoDB Model</h4>

When 'rucore' is executed extracted, data is parsed into this model and inserted into a MongoDB database.

```javascript
{
  courseTitle: {
    type: String,
  },
  courseIndex: {
    type: String,
  },
  credits: {
    type: String,
  },
  coreCodes: [{type: String}],
}
```

<h4>RU Class MongoDB Model</h4>

When 'ruclass' is executed, extracted data is parsed into this model and inserted into a MongoDB database.

```javascript
{
  buildingCode: {
    type: String,
  },
  campus: {
    type: String,
  },
  room: {
    type: String,
  },
  days: {
    monday: [{type: String}],
    tuesday: [{type: String}],
    wednesday: [{type: String}],
    thursday: [{type: String}],
    friday: [{type: String}]
  }
}
```
## License

  [ISC](https://opensource.org/licenses/ISC)
