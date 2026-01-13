# Annie's Room - Implementation Guide
## The Final Door - Asylum Escape Room

---

## 📖 Story Summary

**Annie** is a 12-year-old girl with supernatural fire powers who was manipulated by **Scratcher**, a demon trapped in a teddy bear. Annie found Scratcher at a midnight altar in a forest grove near her family's mountain cottage. The demon gave her fire manipulation abilities, claiming to protect her family from bandits and thieves.

When brigands attacked their home, Annie unleashed her powers in fear, burning the attackers to ash - but also accidentally destroying her home and killing her mother, father, and older sister. Their screams as they burned alive haunt her.

A scientist studying the supernatural brought Annie to the asylum to study her powers, separating her from Scratcher. She was locked in this cell, where she desperately tried to escape through a vent under the desk.

---

## 🎮 Game Flow

### **Objective:**
Piece together Annie's tragic story and escape through the vent to get the cell key.

### **Player Journey:**
1. **Enter Room** → Player spawns in Annie's locked cell
2. **Explore** → Discover 8 interactive objects
3. **Collect Clues** → Find 5 key story elements
4. **Solve Story Puzzle** → Understand what happened to Annie
5. **Find Tool** → Get screwdriver from Room 2 (Bathroom/Janitor's Closet)
6. **Open Vent** → Use screwdriver to unscrew the vent grate
7. **Escape** → Crawl through vent, grab cell key on the other side
8. **Unlock Door** → Use cell key to unlock the door (for multiplayer/co-op)

---

## 🔍 Interactive Game Objects

### **1. Cell Door** (`annies-door`)
- **Actions:** `inspect`, `open`
- **Description:** Heavy wooden door, no inside handle, locked from outside
- **Clue:** Scratch marks near the bottom from desperate escape attempts
- **Related:** Vent, cell key

### **2. Old Wooden Desk** (`wooden-desk`)
- **Actions:** `open`, `inspect`, `move`
- **Description:** Weathered desk bolted to floor, covered in small claw marks
- **Contains:** Annie's letters in drawers
- **Clue:** Draft coming from underneath reveals the vent
- **Related:** Annies-letters, scratcher-bear, vent, claw-marks

### **3. Air Vent** (`vent`)
- **Actions:** `inspect`, `open`, `use`
- **Description:** Metal grate underneath desk, secured with screws
- **Clue:** Can see cell key hanging on hook beyond the grate
- **Solution:** Use screwdriver to unscrew → crawl through → grab key
- **Requires:** Screwdriver from Room 2
- **Related:** Cell-key, wooden-desk

### **4. Charred Wall** (`charred-wall`)
- **Actions:** `inspect`
- **Description:** Blackened, charred wall with ash writing
- **Clues:** 
  - "Scratcher promised to protect us"
  - "I didn't mean to hurt them. The fire... it just happened."
- **Related:** Scratcher-bear, burnt-drawing, annies-letters

### **5. Metal Bed Frame** (`metal-bed`)
- **Actions:** `inspect`, `move`
- **Description:** Small metal bed with thin mattress, squeaky springs
- **Clue:** Small teddy-bear-sized indentation on pillow
- **Hidden Item:** Burnt drawing under mattress
- **Related:** Scratcher-bear, burnt-drawing, claw-marks

### **6. Scratcher (Teddy Bear)** (`scratcher-bear`)
- **Actions:** `inspect`, `use`
- **Description:** Worn teddy bear with one missing button eye, metal claws on paws
- **Clues:**
  - Tag: "Found at midnight altar, forest grove"
  - Smells of sulfur and ash
  - Metal claws match claw marks throughout room
- **Effect:** Touching it causes whispers, unnatural warmth, room gets colder
- **Related:** All story clues

### **7. Claw Marks** (`claw-marks`)
- **Actions:** `inspect`
- **Description:** Small claw marks on desk, bed, floor, door
- **Clue:** Too small for human, matches Scratcher's metal claws
- **Reveals:** The demon could move independently of the bear
- **Related:** Scratcher-bear, wooden-desk, metal-bed

### **8. Burnt Drawing** (`burnt-drawing`)
- **Actions:** `inspect`
- **Description:** Child's drawing of teddy bear with red eyes and sharp teeth
- **Text:** "My friend Scratcher - he helps me" / "He showed me the fire. He says I'm special."
- **Location:** Hidden under mattress
- **Related:** Scratcher-bear, annies-letters

---

## 📜 Collectible Items

### **Annie's Letters** (`annies-letters`)
**Type:** Clue / Document
**Location:** Inside desk drawers
**Content:**
1. "Dear Mother and Father, I miss home so much. I found a friend in the forest - he talks to me. His name is Scratcher. - Annie"
2. "Scratcher says I have special gifts. He's teaching me things. Father would be proud. - Annie"
3. "The brigands came last night. I was so scared. Scratcher said to defend us. I didn't mean for everyone to... the fire just..." (rest burnt)
4. "They took Scratcher away. I'm alone here. I need to escape. The vent under the desk - I think I can fit through. If only I had a tool..." (unfinished)

### **Cell Key** (`cell-key`)
**Type:** Key
**Location:** Beyond the vent, hanging on a hook
**Use:** Unlocks Annie's cell door
**How to Get:** Crawl through vent after unscrewing it with screwdriver

### **Burnt Drawing** (`burnt-drawing`)
**Type:** Clue / Document
**Location:** Under mattress on bed
**Content:** Child's drawing of demonic teddy bear
**Reveals:** Annie knew Scratcher was dangerous

### **Screwdriver** (`screwdriver`)
**Type:** Tool
**Location:** Room 2 - Bathroom/Janitor's Closet (in janitor's jacket pocket)
**Use:** Unscrew vent grate to escape

---

## 🧩 Puzzles

### **Puzzle 1: Annie's Story** (`annies-story-puzzle`)
**Type:** Collection / Discovery
**Objective:** Find all 5 clues to piece together Annie's tragic story

**Required Clues:**
1. ✅ Annie's Letters (desk drawers)
2. ✅ Burnt Drawing (under mattress)
3. ✅ Charred Wall writings (inspect wall)
4. ✅ Scratcher the Teddy Bear (examine bear)
5. ✅ Claw Marks (inspect marks around room)

**Reward:** Understanding the full story reveals the vent escape route

**Story Beats:**
- Annie found Scratcher at a midnight altar
- Scratcher gave her fire powers
- She accidentally killed her family while defending them
- She's imprisoned in the asylum
- Scratcher was taken from her
- She planned to escape via the vent

### **Puzzle 2: Vent Escape** (`vent-escape-puzzle`)
**Type:** Item-based / Multi-room
**Objective:** Open the vent and crawl through to get the cell key

**Steps:**
1. Discover vent under desk (inspect desk)
2. Realize vent is screwed shut (inspect vent)
3. See cell key beyond the grate (inspect vent)
4. Go to Room 2 (Bathroom/Janitor's Closet)
5. Find screwdriver in janitor's jacket pocket
6. Return to Room 1
7. Use screwdriver on vent (action: use)
8. Crawl through vent
9. Grab cell key from hook
10. Unlock cell door

**Required Item:** Screwdriver (from Room 2)
**Reward:** Cell key → freedom

---

## 🎭 Atmosphere & Mood

### **Visual Elements:**
- Dim, cold lighting
- Charred, blackened walls with ash
- Worn metal bed frame
- Bolted wooden desk with claw marks
- Teddy bear with one missing eye on shelf
- Heavy door with no inside handle

### **Audio:**
- Creaking metal bed springs
- Distant whispers (when near Scratcher)
- Faint wind through vent
- Subtle crying sounds
- Eerie silence broken by sudden sounds

### **Emotional Tone:**
- **Tragic:** Annie's innocent trust led to her family's death
- **Eerie:** Scratcher's demonic presence lingers
- **Desperate:** Annie's failed escape attempts visible everywhere
- **Empathetic:** Players should feel sympathy for Annie
- **Unsettling:** The demon's manipulation is disturbing

### **Themes:**
- Innocence corrupted
- Demonic manipulation
- Tragic consequences of power
- Desperate isolation
- Loss and grief

---

## 🗺️ Connection to Room 2

**Room 2: Bathroom & Janitor's Closet**
- Contains the **screwdriver** needed to open the vent
- Players must navigate to Room 2 to find this tool
- Encourages exploration of multiple rooms
- Creates interdependency between rooms

**Multi-room Puzzle Flow:**
1. Annie's Room → Discover you need a tool
2. Explore → Find Bathroom/Janitor's Closet
3. Search → Find screwdriver in jacket pocket
4. Return → Annie's Room to use screwdriver
5. Escape → Through vent to get key

---

## 🎯 Win Condition

**Primary Goal:** Escape Annie's Room

**Success Criteria:**
1. ✅ Discover all 5 story clues (optional but encouraged)
2. ✅ Find screwdriver in Room 2
3. ✅ Unscrew vent grate
4. ✅ Crawl through vent
5. ✅ Obtain cell key
6. ✅ Unlock cell door

**Multiplayer Note:**
- First player crawls through vent, gets key
- Player unlocks door from **inside** (not outside)
- Other players can then exit through the door
- Cooperative gameplay encouraged

---

## 💡 Design Notes

### **What Makes This Room Great:**
1. **Rich Narrative:** Complete tragic backstory to discover
2. **Environmental Storytelling:** Every object tells part of the story
3. **Multi-layered Puzzle:** Story puzzle + physical escape puzzle
4. **Cross-room Dependency:** Requires visiting Room 2
5. **Atmospheric Horror:** Dark, tragic, unsettling mood
6. **Player Agency:** Multiple objects to interact with
7. **Rewarding Exploration:** Hidden items (drawing under mattress)
8. **Emotional Impact:** Players empathize with Annie

### **Difficulty Balance:**
- **Easy Mode:** Show obvious hints about vent location
- **Medium Mode:** Require finding all letters to understand vent
- **Hard Mode:** No direct hints, players must thoroughly explore

### **Replayability:**
- Rich lore encourages multiple playthroughs
- Players may miss clues on first attempt
- Story depth rewards careful exploration

---

## 🚀 Implementation Checklist

### **Database (MongoDB):**
- [ ] Create/update 8 game objects in `game objects` collection
- [ ] Add 4 items to `items` collection
- [ ] Update Room 1 in `rooms` collection
- [ ] Add 2 puzzles to puzzle tracking system

### **Backend (Spring Boot):**
- [ ] Ensure ActionController handles all action types
- [ ] Implement vent unlocking logic (requires screwdriver)
- [ ] Track player progress on story puzzle
- [ ] Handle cell key acquisition
- [ ] Implement door unlocking with cell key

### **Frontend (React):**
- [ ] Create UI for 8 interactive objects
- [ ] Display Annie's letters when found
- [ ] Show burnt drawing when bed is moved
- [ ] Display charred wall text when inspected
- [ ] Create vent interaction (use screwdriver)
- [ ] Show "crawl through" animation
- [ ] Display cell key acquisition
- [ ] Track story clue collection (5/5)
- [ ] Atmospheric sound effects
- [ ] Dark, eerie visual theme

### **Testing:**
- [ ] Can player find all 5 story clues?
- [ ] Does screwdriver work on vent?
- [ ] Can player crawl through vent?
- [ ] Does cell key unlock door?
- [ ] Are all hint messages clear?
- [ ] Is the atmosphere appropriately eerie?
- [ ] Do all related objects connect properly?

---

## 📝 Future Enhancements

1. **Voice Acting:** Annie's whispers/diary readings
2. **Animations:** Scratcher's eye following player
3. **Dynamic Lighting:** Flickering when near Scratcher
4. **Sound Design:** Creaking, whispers, crying
5. **Particle Effects:** Ash floating in air
6. **Easter Eggs:** Hidden messages in ash
7. **Difficulty Scaling:** Adjust clue visibility
8. **Time Pressure:** Optional timer mode
9. **Multiplayer Roles:** Assign story-discovery tasks
10. **Achievement System:** "Found all Annie's letters"

---

**Created:** January 5, 2026
**Room:** Annie's Room (Room #1)
**Theme:** The Final Door - Asylum Escape
**Status:** Design Complete - Ready for Implementation

