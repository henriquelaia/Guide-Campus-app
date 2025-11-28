const express = require('express');
const cors = require('cors');
const fs = require('fs');
const path = require('path');

const app = express();
const PORT = 3000;

// Middleware
app.use(cors());
app.use(express.json());

// Helper function to read data
const readData = (filename) => {
    const filePath = path.join(__dirname, 'data', filename);
    try {
        const data = fs.readFileSync(filePath, 'utf8');
        return JSON.parse(data);
    } catch (err) {
        console.error(`Error reading ${filename}:`, err);
        return [];
    }
};

// Routes
app.get('/api/locations', (req, res) => {
    const locations = readData('locations.json');
    res.json(locations);
});

app.get('/api/professors', (req, res) => {
    const professors = readData('professors.json');
    res.json(professors);
});

app.get('/api/rooms', (req, res) => {
    const rooms = readData('rooms.json');
    res.json(rooms);
});

// Start server
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
    console.log(`- Locations: http://localhost:${PORT}/api/locations`);
    console.log(`- Professors: http://localhost:${PORT}/api/professors`);
    console.log(`- Rooms: http://localhost:${PORT}/api/rooms`);
});
