# Annie's Room - API Implementation Guide
## Service Layer Complete - Ready to Use

---

## ✅ Implementation Status

### **Services Implemented:**
- ✅ **GameStateService** - Tracks player progress, clues, puzzles, vent status
- ✅ **ActionService** - Enhanced with item requirements, clue tracking, special actions
- ✅ **AsylumPuzzleServiceImpl** - Annie's story puzzle & vent escape puzzle
- ✅ **InventoryService** - Item management (already working)
- ✅ **PlayerService** - Player creation & management (already working)
- ✅ **RoomService** - Room data retrieval (already working)

### **Controllers Implemented:**
- ✅ **ActionController** - Perform actions on game objects
- ✅ **PuzzleController** - Solve puzzles, check progress
- ✅ **GameProgressController** - Track overall player progress
- ✅ **RoomObjectsController** - List all objects in a room
- ✅ **PlayerController** - Player management (already working)
- ✅ **InventoryController** - Inventory management (already working)
- ✅ **RoomController** - Room retrieval (already working)

---

## API Endpoints

### **1. Player Management**

#### Create Player
```http
POST /api/players
Content-Type: application/json

{
  "playerName": "Jack",
  "background": "Explorer",
  "difficultyLevel": "Easy",
  "specialAbility": "Time stop",
  "starterItem": "notepad"
}
```

#### Get Player
```http
GET /api/players/{playerId}
```

#### Get Player by Name (Login)
```http
GET /api/players/by-name/Jack
```

---

### **2. Room & Objects**

#### Get All Rooms
```http
GET /api/rooms
```

#### Get All Objects in Room
```http
GET /api/rooms/1/objects
```
**Response:**
```json
[
  {
    "id": "annies-door",
    "name": "Cell Door",
    "interactionId": "door",
    "description": "A heavy wooden door...",
    "interactionType": "INTERACT"
  },
  {
    "id": "wooden-desk",
    "name": "Old Wooden Desk",
    "interactionId": "desk",
    "description": "A weathered wooden desk...",
    "interactionType": "INTERACT"
  }
  // ... 6 more objects
]
```

#### Get Object Details
```http
GET /api/players/{playerId}/rooms/1/objects/desk/actions
```
**Response:**
```json
{
  "name": "Old Wooden Desk",
  "roomId": "1",
  "interactionId": "desk",
  "description": "A weathered wooden desk with multiple drawers. The wood is scarred with small claw marks."
}
```

---

### **3. Actions on Objects**

#### Perform Action
```http
POST /api/players/{playerId}/rooms/1/objects/desk/actions
Content-Type: application/json

{
  "actionType": "open"
}
```

**Response:**
```json
{
  "success": true,
  "result": "You open the drawers and find several old letters written by Annie to her family. They speak of a 'friend' named Scratcher and strange powers she's discovered."
}
```

#### Examples of Actions:

**Inspect Desk:**
```http
POST /api/players/{playerId}/rooms/1/objects/desk/actions
{"actionType": "inspect"}
```

**Move Bed:**
```http
POST /api/players/{playerId}/rooms/1/objects/bed/actions
{"actionType": "move"}
```

**Inspect Charred Wall:**
```http
POST /api/players/{playerId}/rooms/1/objects/charredWall/actions
{"actionType": "inspect"}
```

**Use Screwdriver on Vent:**
```http
POST /api/players/{playerId}/rooms/1/objects/vent/actions
{"actionType": "use"}
```
**Response (if player has screwdriver):**
```json
{
  "success": true,
  "result": "You use the screwdriver to unscrew the vent. The grate falls away, revealing a dark passage. You crawl through and emerge on the other side, grabbing the cell key from the hook.\n\nYou've opened the vent! You can now escape through it."
}
```

**Response (if player doesn't have screwdriver):**
```json
{
  "success": false,
  "result": "You need a screwdriver to do that."
}
```

---

### **4. Inventory Management**

#### Get Player Inventory
```http
GET /api/players/{playerId}/inventory
```

#### Add Item to Inventory
```http
POST /api/players/{playerId}/inventory/items
Content-Type: application/json

{
  "id": "screwdriver",
  "name": "Screwdriver",
  "description": "A rusty screwdriver",
  "type": "tool",
  "use": "unscrew"
}
```

#### Remove/Use Item
```http
DELETE /api/players/{playerId}/inventory/items/screwdriver
```

---

### **5. Puzzle Management**

#### Solve Story Puzzle
```http
POST /api/players/{playerId}/rooms/1/puzzles/annies-story-puzzle/solve
```
**Response (if all 5 clues found):**
```json
{
  "solved": true,
  "success": true,
  "message": "You've pieced together Annie's tragic story. The vent under the desk is your way out!"
}
```

**Response (if not all clues found):**
```json
{
  "solved": false,
  "success": false,
  "message": "You've found 3/5 clues. Keep exploring to discover the full story."
}
```

#### Solve Vent Escape Puzzle
```http
POST /api/players/{playerId}/rooms/1/puzzles/vent-escape-puzzle/solve
```

#### Get Puzzle Progress
```http
GET /api/players/{playerId}/rooms/1/puzzles/annies-story-puzzle/progress
```
**Response:**
```json
{
  "cluesFound": 3,
  "cluesRequired": 5,
  "completed": false
}
```

---

### **6. Player Progress**

#### Get Complete Player Progress
```http
GET /api/players/{playerId}/progress
```
**Response:**
```json
{
  "discoveredClues": [
    "annies-letters",
    "charred-wall",
    "claw-marks"
  ],
  "clueCount": 3,
  "completedPuzzles": [],
  "ventOpened": false,
  "currentRoom": "1"
}
```

#### Get Current Room
```http
GET /api/players/{playerId}/progress/room
```

#### Move to Room
```http
POST /api/players/{playerId}/progress/room/2
```

#### Reset Progress
```http
POST /api/players/{playerId}/progress/reset
```

---

## Complete Gameplay Flow

### **Step 1: Create Player**
```http
POST /api/players
{
  "playerName": "Jack",
  "background": "Explorer",
  "difficultyLevel": "Easy",
  "specialAbility": "Time stop",
  "starterItem": "notepad"
}
```
**Result:** Player created with ID, starts in Room 1

---

### **Step 2: Explore Annie's Room**

#### List all objects:
```http
GET /api/rooms/1/objects
```
**Result:** See 8 interactive objects

#### Examine desk:
```http
POST /api/players/{playerId}/rooms/1/objects/desk/actions
{"actionType": "inspect"}
```
**Result:** Discover vent underneath, clue "claw-marks" tracked

#### Open desk drawers:
```http
POST /api/players/{playerId}/rooms/1/objects/desk/actions
{"actionType": "open"}
```
**Result:** Find Annie's letters, clue "annies-letters" tracked

#### Move bed:
```http
POST /api/players/{playerId}/rooms/1/objects/bed/actions
{"actionType": "move"}
```
**Result:** Find burnt drawing, clue "burnt-drawing" tracked

#### Inspect charred wall:
```http
POST /api/players/{playerId}/rooms/1/objects/charredWall/actions
{"actionType": "inspect"}
```
**Result:** Read ash writing, clue "charred-wall" tracked

#### Examine Scratcher bear:
```http
POST /api/players/{playerId}/rooms/1/objects/teddyBear/actions
{"actionType": "inspect"}
```
**Result:** Discover demonic nature, clue "scratcher-bear" tracked

---

### **Step 3: Check Story Puzzle Progress**
```http
GET /api/players/{playerId}/rooms/1/puzzles/annies-story-puzzle/progress
```
**Result:**
```json
{
  "cluesFound": 5,
  "cluesRequired": 5,
  "completed": true
}
```

---

### **Step 4: Solve Story Puzzle**
```http
POST /api/players/{playerId}/rooms/1/puzzles/annies-story-puzzle/solve
```
**Result:** Puzzle solved, hints at vent escape

---

### **Step 5: Go to Room 2 for Screwdriver**
```http
POST /api/players/{playerId}/progress/room/2
```
**Result:** Player moves to Bathroom/Janitor's Closet

#### Find screwdriver:
```http
POST /api/players/{playerId}/inventory/items
{
  "id": "screwdriver",
  "name": "Screwdriver",
  "description": "A rusty screwdriver",
  "type": "tool",
  "use": "unscrew"
}
```

---

### **Step 6: Return to Room 1**
```http
POST /api/players/{playerId}/progress/room/1
```

---

### **Step 7: Use Screwdriver on Vent**
```http
POST /api/players/{playerId}/rooms/1/objects/vent/actions
{"actionType": "use"}
```
**Result:** 
- Vent opens
- Player crawls through
- Finds cell key
- Vent escape puzzle marked complete

---

### **Step 8: Solve Vent Escape Puzzle**
```http
POST /api/players/{playerId}/rooms/1/puzzles/vent-escape-puzzle/solve
```
**Result:** Puzzle completed!

---

### **Step 9: Add Cell Key to Inventory**
```http
POST /api/players/{playerId}/inventory/items
{
  "id": "cell-key",
  "name": "Cell Key",
  "description": "An old iron key",
  "type": "key",
  "use": "unlock"
}
```

---

### **Step 10: Check Final Progress**
```http
GET /api/players/{playerId}/progress
```
**Result:**
```json
{
  "discoveredClues": [
    "annies-letters",
    "burnt-drawing",
    "charred-wall",
    "scratcher-bear",
    "claw-marks"
  ],
  "clueCount": 5,
  "completedPuzzles": [
    "annies-story-puzzle",
    "vent-escape-puzzle"
  ],
  "ventOpened": true,
  "currentRoom": "1"
}
```

** Annie's Room Complete!**

---

## Action Types Supported

| Action Type | Description | Example Objects |
|-------------|-------------|-----------------|
| `inspect` | Examine object closely | All objects |
| `open` | Open doors, drawers, containers | Door, Desk |
| `move` | Move or lift object | Bed, Desk (bolted) |
| `use` | Use item on object | Vent (requires screwdriver) |

---

## Clue Tracking

### **5 Required Clues for Story Puzzle:**
1.  `annies-letters` - Found by opening desk
2.  `burnt-drawing` - Found by moving bed
3.  `charred-wall` - Found by inspecting wall
4.  `scratcher-bear` - Found by inspecting teddy bear
5.  `claw-marks` - Found by inspecting desk/bed/marks

### **Automatic Tracking:**
- Clues are automatically tracked when discovered via actions
- Progress persists in `GameStateService`
- Check progress anytime with `/progress` endpoint

---

## Puzzle Logic

### **Story Puzzle (`annies-story-puzzle`):**
- **Completion:** Find all 5 clues
- **Solution:** Automatic when all clues discovered
- **Reward:** Understanding that vent is the escape route

### **Vent Escape Puzzle (`vent-escape-puzzle`):**
- **Requirement:** Screwdriver from Room 2
- **Completion:** Use screwdriver on vent
- **Reward:** Cell key, freedom

---

## Key Features

**Item Requirements** 
- **Automatic Clue Tracking** - Discovers tracked automatically
- **Progress Persistence** - Player progress saved in memory
- **Multi-room Support** - Move between rooms
- **Puzzle State** - Track completed puzzles
- **Inventory Integration** - Check for required items
- **Error Handling** - Error messages


---

## Testing Checklist

- [ ] Create player successfully
- [ ] List all Room 1 objects (8 objects)
- [ ] Perform inspect actions on all objects
- [ ] Open desk to find letters (clue tracked)
- [ ] Move bed to find drawing (clue tracked)
- [ ] Check puzzle progress (5/5 clues)
- [ ] Solve story puzzle
- [ ] Try to use vent without screwdriver (fails)
- [ ] Add screwdriver to inventory
- [ ] Use screwdriver on vent (succeeds)
- [ ] Solve vent escape puzzle
- [ ] Check final progress (all complete)

---

**Status:** ✅ Service Layer Fully Implemented
**Build:** ✅ BUILD SUCCESSFUL
**Ready for:** Frontend Integration

---

**Created:** January 5, 2026
**Last Build:** Successful
**API Version:** 1.0.0

