# MongoDB Import Instructions - Annie's Room

## 📋 Overview
This guide explains how to import Annie's Room data into your MongoDB database.

---

## ⚠️ Important: What NOT to Import

**DO NOT import `annies_room_complete_design.json` directly into MongoDB!**

This file is a **design reference document** for developers. It contains combined data that should be split across multiple collections.

---

## ✅ What TO Import

Import these 3 files into their respective collections:

### 1. **Game Objects** → `game objects` collection
- **File:** `game_objects.json`
- **Contains:** 8 interactive objects (door, desk, vent, wall, bed, bear, marks, drawing)
- **Collection Name:** `game objects` (note the space!)

### 2. **Items** → `items` collection
- **File:** `item.json`
- **Contains:** 14 items (flashlight, notepad, keys, fuses, Annie's letters, etc.)
- **Collection Name:** `items`

### 3. **Rooms** → `rooms` collection
- **File:** `rooms.json`
- **Contains:** Room definitions (Annie's Room + Bathroom)
- **Collection Name:** `rooms`

---

## 🚀 Quick Start (Automated)

### **Option 1: Using PowerShell (Windows)**

1. **Edit the script** with your MongoDB URI:
   ```powershell
   notepad import-annies-room.ps1
   # Replace YOUR_MONGODB_URI with your actual connection string
   ```

2. **Run the script:**
   ```powershell
   .\import-annies-room.ps1
   ```

### **Option 2: Using Bash (Linux/Mac)**

1. **Make script executable:**
   ```bash
   chmod +x import-annies-room.sh
   ```

2. **Edit the script** with your MongoDB URI:
   ```bash
   nano import-annies-room.sh
   # Replace YOUR_MONGODB_URI with your actual connection string
   ```

3. **Run the script:**
   ```bash
   ./import-annies-room.sh
   ```

---

## 🔧 Manual Import (Command Line)

### **Prerequisites:**
- MongoDB tools installed (`mongoimport` command available)
- Your MongoDB connection URI

### **Commands:**

Replace `YOUR_MONGODB_URI` with your actual connection string:

```bash
# Import Game Objects
mongoimport --uri "YOUR_MONGODB_URI" \
  --db Game \
  --collection "game objects" \
  --file "src/main/resources/documentation/game_objects.json" \
  --jsonArray \
  --drop

# Import Items
mongoimport --uri "YOUR_MONGODB_URI" \
  --db Game \
  --collection items \
  --file "src/main/resources/documentation/item.json" \
  --jsonArray \
  --drop

# Import Rooms
mongoimport --uri "YOUR_MONGODB_URI" \
  --db Game \
  --collection rooms \
  --file "src/main/resources/documentation/rooms.json" \
  --jsonArray \
  --drop
```

**Note:** The `--drop` flag will **replace** existing data in these collections. Remove it if you want to append instead.

---

## 🖥️ GUI Import (MongoDB Compass)

### **Steps:**

1. **Open MongoDB Compass**
2. **Connect** to your database using your connection string
3. **Select database:** `Game`
4. **For each collection:**

#### **Import Game Objects:**
   - Click collection: `game objects`
   - Click "ADD DATA" → "Import JSON or CSV file"
   - Select: `src/main/resources/documentation/game_objects.json`
   - Format: JSON
   - Click "Import"

#### **Import Items:**
   - Click collection: `items`
   - Click "ADD DATA" → "Import JSON or CSV file"
   - Select: `src/main/resources/documentation/item.json`
   - Format: JSON
   - Click "Import"

#### **Import Rooms:**
   - Click collection: `rooms`
   - Click "ADD DATA" → "Import JSON or CSV file"
   - Select: `src/main/resources/documentation/rooms.json`
   - Format: JSON
   - Click "Import"

---

## 📊 Expected Results

After successful import, you should have:

### **`game objects` collection:**
- 8 documents (all Annie's Room interactive objects)
- IDs: `annies-door`, `wooden-desk`, `vent`, `charred-wall`, `metal-bed`, `scratcher-bear`, `claw-marks`, `burnt-drawing`

### **`items` collection:**
- 14 documents (all game items)
- Includes: `flashlight`, `notepad`, `cell-key`, `annies-letters`, `burnt-drawing`, `screwdriver`, fuses, etc.

### **`rooms` collection:**
- 2 documents (Annie's Room + Bathroom)
- IDs: `1` (Annie's Room), `2` (Bathroom/Janitor's Closet)

---

## ✅ Verification

### **Verify Game Objects:**
```javascript
// In MongoDB shell or Compass
db.getCollection("game objects").find({ roomId: "1" }).count()
// Should return: 8
```

### **Verify Items:**
```javascript
db.items.find({ type: "clue" }).count()
// Should return at least 4 (including annies-letters, burnt-drawing, etc.)
```

### **Verify Rooms:**
```javascript
db.rooms.findOne({ _id: "1" })
// Should return Annie's Room with puzzles
```

---

## 🔄 Update Existing Data

If you already have data in MongoDB and want to update it:

### **Option 1: Replace Everything (--drop)**
- Use the `--drop` flag (included in scripts)
- Completely replaces collection data

### **Option 2: Append New Data**
- Remove the `--drop` flag
- Adds new documents, keeps existing ones
- May create duplicates if IDs match

### **Option 3: Upsert (Recommended for Updates)**
```bash
mongoimport --uri "YOUR_MONGODB_URI" \
  --db Game \
  --collection "game objects" \
  --file "src/main/resources/documentation/game_objects.json" \
  --jsonArray \
  --mode upsert \
  --upsertFields _id
```

---

## ⚠️ Troubleshooting

### **Error: "mongoimport: command not found"**
- Install MongoDB Database Tools: https://www.mongodb.com/try/download/database-tools

### **Error: "Failed to connect"**
- Check your MongoDB URI is correct
- Ensure MongoDB Atlas IP whitelist includes your IP
- Verify username/password in connection string

### **Error: "namespace not found"**
- The collection will be created automatically on first import
- This is normal for new databases

### **Duplicate Key Errors**
- Use `--drop` flag to replace all data
- Or use `--mode upsert` to update existing documents

---

## 📁 File Reference

| File | Purpose | Size | Import? |
|------|---------|------|---------|
| `annies_room_complete_design.json` | Design document | 12KB | ❌ No |
| `game_objects.json` | Interactive objects | ~8KB | ✅ Yes |
| `item.json` | Collectible items | ~3KB | ✅ Yes |
| `rooms.json` | Room definitions | ~4KB | ✅ Yes |
| `ANNIES_ROOM_IMPLEMENTATION_GUIDE.md` | Developer guide | 15KB | ❌ No |
| `import-annies-room.ps1` | Windows import script | 1KB | 🔧 Run |
| `import-annies-room.sh` | Linux/Mac import script | 1KB | 🔧 Run |

---

## 🎯 Next Steps After Import

1. ✅ **Verify data** in MongoDB Compass
2. ✅ **Test API endpoints** (get room, get objects, perform actions)
3. ✅ **Start backend development** for puzzle logic
4. ✅ **Build frontend** using imported data
5. ✅ **Implement vent escape mechanic**
6. ✅ **Add Room 2** (Bathroom/Janitor's Closet with screwdriver)

---

**Need Help?** Check the `ANNIES_ROOM_IMPLEMENTATION_GUIDE.md` for full implementation details.

**Last Updated:** January 5, 2026

