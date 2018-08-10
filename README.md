<h1>Rutgers SOC Parser</h1>

Rutgers SOC Parser is a command line project written in Java that allows you to pull large amounts of data from Rutgers SOC API and populate databases accordingly.

<h2>How to use</h2>

| Command | Description 
| --- | --- 
| `rucore` | Extracts and parses course data into a MongoDB Model
| `ruclass` | Extracts and parses class meeting time data into a MongoDB Model

<h2>Models</h2>

<h3>RU Core MongoDB Model</h3>

When 'rucore' is executed data is parsed into this model and inserted into a MongoDB database.

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
