# The Final Door - AI Assistant Instructions

## Project Overview
This is a **backend Spring Boot application** for **The Final Door**, a multi-themed escape room game platform. The frontend is served by a separate **JavaScript React** application that communicates with this backend via REST APIs.

The platform hosts multiple themed escape room experiences including Asylum, Hospital, Jail, Tomb, and more, each with unique puzzles, narratives, and game mechanics.

## AI Assistant Guidelines

### Code Style Preferences
- **No unnecessary comments**: Do not add comments to code unless absolutely necessary for complex logic that isn't self-explanatory
- **No summary files**: Do not generate `.md` files or summary documents explaining what was done - the developer can read code and will add documentation if needed
- **Clean code**: Write self-documenting code with clear variable/method names instead of relying on comments
- **Action over explanation**: Focus on making changes rather than explaining what you're going to do

## Technology Stack

### Backend (This Project)
- **Framework**: Spring Boot 3.2.0
- **Java Version**: 17
- **Build Tool**: Gradle
- **Database**: MongoDB Atlas (cloud-hosted)
- **Key Dependencies**:
  - Spring Data MongoDB
  - Spring Web (REST APIs)
  - Spring WebSocket (real-time communication)
  - Spring WebFlux (reactive programming)
  - Lombok (code generation)
  - OpenAPI Generator (API specification)

### Frontend (Separate Project)
- **Framework**: React
- **Language**: JavaScript
- Consumes REST APIs from this backend

## Project Structure

### Package Organization
The project follows a **layered architecture** with clear separation between **core** game logic and **theme-specific** implementations:

#### Core Package (`com.thefinaldoor.core`)
**Generic, reusable game components that work across ALL themes (asylum, jail, pyramid, etc.):**

**What BELONGS in Core:**
- Player management (creation, retrieval, state)
- Inventory system (abstract inventory operations)
- Base action/interaction system
- Puzzle mechanics (abstract puzzle solving)
- Game session management
- Common exceptions and error handling
- Shared constants
- Base repositories for core entities

**Core Structure:**
- **Controllers**: REST API endpoints (`core.controllers`)
  - `PlayerController` - Player creation and retrieval (CORE - all games have players)
  - `InventoryController` - Player inventory operations (CORE - all games have inventories)
  - `ActionController` - Action processing (CORE - all games have actions)
  - `GameController` - Game session management
  
- **Services**: Business logic layer (`core.services`)
  - `PlayerService` - Player creation and management (CORE)
  - `InventoryService` - Inventory management (CORE)
  - `ActionService` - Base action processing
  - `PuzzleService` - Abstract puzzle mechanics
  - `DescriptionService` - Dynamic text descriptions
  
- **Models**: Domain objects (`core.models`)
  - `Player` - Player entity (CORE - all games have players)
  - `Inventory` - Inventory document (CORE)
  - `AbstractInventory`, `DefaultInventory`, `InventoryImpl` - Inventory implementations
  - `Item` - Base item model (CORE - all games have items)
  - `Action` - Base action model (CORE)
  
- **Repositories**: Data access layer (`core.repositories`)
  - `PlayerRepository` - Player persistence (CORE)
  - `InventoryRepository` - Inventory persistence (CORE)
  - `ItemRepository` - Item retrieval (CORE)
  
- **Exceptions**: Error handling (`core.exceptions`)
  - `FinalDoorExceptionHandler` - Global exception handler
  - `ErrorResponse` - Standardized error response
  
- **Constants**: Application-wide constants (`core.constants`)
  - `Constants` - Shared constants across all games

#### Theme-Specific Package (`com.thefinaldoor.asylum`)
**Asylum-themed escape room implementation - ONLY asylum-specific logic:**

**What BELONGS in Theme Packages (asylum, jail, pyramid, etc.):**
- Theme-specific room definitions
- Theme-specific puzzles and solutions
- Theme-specific game objects and interactions
- Theme-specific narrative/story elements
- Custom game mechanics unique to that theme
- Theme-specific controllers (if needed beyond core)

**Asylum Structure:**
- **Controllers**: Theme-specific endpoints (`asylum.controllers`)
  - `AsylumRoomController` - Asylum room operations
  - `RoomController` - Room retrieval and management
  - `InteractionController` - Object interactions (asylum-specific)
  - `PuzzleController` - Puzzle operations (asylum-specific)
  - `ItemManagementController` - Item CRUD operations
  
- **Services**: Theme-specific logic (`asylum.services`)
  - `AsylumPuzzleServiceImpl` - Asylum puzzle implementation
  - `RoomService` - Asylum room data retrieval
  - `InteractionService` - Asylum object interaction handling
  - `StarterItemService` - Asylum starter item assignment
  
- **Models**: Theme domain objects (`asylum.model`)
  - `Room` - Asylum room entity
  - `GameObject` - Interactive objects in asylum rooms
  - `Puzzle` - Asylum puzzle definitions
  - `Clue` - Asylum hint system
  - `InteractionType` - Enum (INSPECT, INTERACT, PICK_UP, OPEN)
  - `AnnieRoom` - Special asylum room type
  - `StoryElements` - Asylum narrative content
  
- **Repositories**: MongoDB data access (`asylum.repositories`)
  - `RoomRepository` - Asylum room retrieval
  - `GameObjectRepository` - Asylum GameObject retrieval
  
- **Constants**: Theme constants (`asylum.constants`)
  - `AsylumConstants` - Asylum-specific constants

### Adding a New Theme (jail, pyramid, etc.)
When adding a new theme, create package: `com.thefinaldoor.<theme-name>`

1. **Reuse Core Components:**
   - Use `core.models.Player` (don't create theme-specific player)
   - Use `core.models.Inventory` and inventory services
   - Use `core.controllers.PlayerController` and `InventoryController`
   - Use `core.repositories.PlayerRepository` and `InventoryRepository`

2. **Create Theme-Specific Components:**
   - Theme rooms (e.g., `JailCell`, `PyramidChamber`)
   - Theme puzzles (e.g., `JailLockPuzzle`, `PyramidHieroglyphPuzzle`)
   - Theme game objects and interactions
   - Theme narrative/story elements
   - Theme-specific controllers ONLY if core controllers aren't sufficient

3. **Follow the asylum package as a template** for structure

### Key Architectural Principles
- ✅ **Core = Reusable** - If multiple themes need it, it belongs in core
- ✅ **Theme = Unique** - Only theme-specific logic in theme packages
- ✅ **Player is ALWAYS core** - Never create theme-specific player classes
- ✅ **Inventory is ALWAYS core** - Never create theme-specific inventory classes
- ✅ **Items are CORE** - Base item model in core, theme-specific item types can extend it
- ✅ **Actions are CORE** - Base action system in core, theme-specific actions can extend it
- ✅ **Controllers in core** - Unless the endpoint is truly theme-specific
- ✅ **Repositories in core** - For core entities (Player, Inventory, Item)

## Database (MongoDB)

### Purpose
MongoDB is used for **read-only retrieval** of static/constant game data and **storage of dynamic game state**:

#### Static/Constant Data (Read-Only):
- **Rooms** - Room definitions, descriptions, connections, puzzles
- **Game Objects** - Interactive objects, their actions, clues
- **Items** - Item definitions and properties
- **Clues** - Hints and puzzle clues
- Other constant game configuration data

#### Dynamic Game State (Read/Write):
- **Players** - Player progress, current room, status, score, time
- **Inventory** - Player inventories (linked to player via inventoryId)

### MongoDB Connection Security

**IMPORTANT:** MongoDB credentials are stored securely and NOT committed to git:

1. **application.yml** (committed to git):
   - Contains default/fallback configuration
   - Uses environment variables: `${MONGODB_URI:mongodb://localhost:27017/Game}`
   - Safe to commit - no secrets

2. **application-local.yml** (gitignored - NEVER commit):
   - Contains actual MongoDB Atlas credentials
   - Used for local development
   - Listed in `.gitignore` to prevent accidental commits
   - Create from `application-local.yml.template`

3. **.env** (gitignored - NEVER commit):
   - Alternative way to store MongoDB URI
   - Listed in `.gitignore`
   - Used by DotenvConfig if present

**To set up local development:**
```bash
# Option 1: Use application-local.yml
cp src/main/resources/application-local.yml.template src/main/resources/application-local.yml
# Edit application-local.yml with your credentials

# Option 2: Use .env file
echo 'MONGODB_URI=mongodb+srv://user:pass@cluster.net/Game' > .env
```

### MongoDB Collections

#### `rooms` Collection
Maps to: `com.thefinaldoor.asylum.model.Room`
```
{
  id: String,
  name: String,
  description: String,
  isLocked: Boolean,
  items: [Item],
  puzzles: [Puzzle],
  hints: [String]
}
```

#### `game objects` Collection
Maps to: `com.thefinaldoor.asylum.model.GameObject`
```
{
  id: String,
  name: String,
  interactionType: String,
  roomId: String,
  interactionId: String,
  description: String,
  clues: [String],
  actions: [Action],
  relatedObjects: [String]
}
```

#### `items` Collection
Maps to: `com.thefinaldoor.asylum.model.Item`
```
{
  id: String,
  type: String,
  state: String,
  content: String
}
```

#### `inventory` Collection
Maps to: `com.thefinaldoor.core.dto.Inventory`
```
{
  id: String,
  playerId: String,
  items: [Item]
}
```

### Data Location
Reference JSON files are stored in `src/main/resources/documentation/`:
- `rooms.json` - Room definitions
- `clue.json` - Clue database
- `game_objects.json` - Interactive objects
- `item.json` - Item catalog
- `Inventory.json` - Inventory structure
- `player.json` - Player template
- `itemsStructure.json` - Item hierarchy
- `Annie_Room_Story.txt` - Narrative content

### Connection
- **Database**: MongoDB Atlas (cloud)
- **Configuration**: `src/main/resources/application.yml`
- **Connection String**: `mongodb+srv://admin:***@cluster0.xn2u0pi.mongodb.net/Game`
- **Database Name**: `Game`
- **Connection managed by**: Spring Data MongoDB

### Repository Pattern
The project uses Spring Data MongoDB repositories for data access:

#### Core Repositories
- `InventoryRepository` - Manages player inventory documents

#### Asylum Repositories
- `RoomRepository` - Queries room data (e.g., `findByName(String name)`)
- `ItemRepository` - Retrieves items from database
- `GameObjectRepository` - Fetches interactive game objects
- `PlayerRepository` - Persists and retrieves player state

All repositories extend `MongoRepository<T, String>` which provides:
- Basic CRUD operations (`save`, `findById`, `findAll`, `delete`)
- Custom query methods via method naming convention
- Native MongoDB query support via `@Query` annotation

## API Specification

### OpenAPI/Swagger
- **Specification File**: `src/main/resources/specifications.yml`
- **Models**: Auto-generated during build via OpenAPI Generator plugin
- **Generated Location**: `build/generated/openapi/`
- **API Version**: 1.0.0
- **Local Server**: `http://localhost:8080`

### API Endpoints

#### Rooms (Tag: Rooms)
- `GET /room/{roomId}` - Get a specific room by ID
  - Returns: Room object with items, puzzles, hints
  
- `GET /all/rooms` - Get all available rooms
  - Returns: Array of Room objects

#### Inventory (Tag: Inventory)
- `GET /inventory/getEntireInventory/{playerId}` - Get full inventory for a player
  - Returns: Array of Item objects
  
- `POST /inventory/add` - Add item to player's inventory
  - Body: `{ playerName, playerId, itemId, itemName }`
  - Returns: Success object
  
- `POST /inventory/{playerId}/{itemId}` - Use or inspect an inventory item
  - Body: ActionRequest
  - Returns: Action result object

#### Player (Tag: Player)
- `POST /player/create` - Create a new player
  - Body: Player object (playerName, background, difficultyLevel, specialAbility, starterItem)
  - Returns: Player creation response
  - Difficulty Levels: Easy, Medium, Hard
  
- `GET /player/get/{playerId}` - Get player by ID
  - Returns: Player object with status, score, time, current room

#### Interactions (Tag: Interactions)
- `GET /interact/{objectName}` - Interact with an object by name
  - Returns: Interaction result
  
- `GET /action/{roomId}/{objectName}` - Get current state of an object
  - Returns: Object state
  
- `POST /action/{roomId}/{objectName}` - Perform action on object
  - Body: ActionRequest (actionType: inspect, use, move, open, close)
  - Returns: Action result

### Action Types
Defined in `ActionRequest` schema:
- **inspect** - Examine an object or item
- **use** - Use an item or interact with object
- **move** - Move to a different location
- **open** - Open doors, containers, etc.
- **close** - Close doors, containers, etc.

### Data Models (OpenAPI Generated)
Key schemas defined in specifications.yml:
- **Room**: id, name, description, items[]
- **Item**: id, type, state, content
- **Player**: playerName, background, difficultyLevel, specialAbility, starterItem
- **ActionRequest**: actionType (enum)

## Development Guidelines

### When Adding Features

#### 1. New API Endpoints
- Define endpoint in `specifications.yml` first
- Generate models: `./gradlew openApiGenerate`
- Create/update controller with `@RestController`
- Implement service layer logic
- Wire dependencies via constructor injection

#### 2. New Game Objects/Items
- Add to MongoDB collection
- Update reference JSON in `src/main/resources/documentation/`
- Verify model mapping (`@Document` annotation)
- Create/update repository if needed

#### 3. New Player Features
- Player state stored in `core.models.Player`
- Update Player model if adding new fields
- Modify `PlayerService` for new logic
- Update OpenAPI spec if API changes needed

#### 4. New Interactions
- Define interaction in `asylum.model.InteractionType` (if new type)
- Add action handlers in `ActionService` or `InteractionService`
- Update GameObject model if needed
- Add clues/responses to database

### Code Style
- Use **Lombok** annotations (`@Data`, `@Service`, `@RestController`, `@Repository`, etc.)
- Follow **Spring Boot best practices**:
  - Controllers handle HTTP, return DTOs
  - Services contain business logic
  - Repositories handle data access only
- Use constructor injection for dependencies (not `@Autowired` on fields)
- Use `MongoRepository` for database operations
- Follow existing package structure (core vs theme-specific)

### Architecture Principles
- **Core package**: Generic, theme-agnostic game logic
- **Theme packages** (asylum): Theme-specific implementations
- Keep controllers thin, services fat
- Use DTOs for API responses, not domain models directly
- Validate input in controllers or services
- Use exceptions for error handling (caught by `FinalDoorExceptionHandler`)

### Testing
- Test files location: `src/test/java/com/thefinaldoor/`
- Use Spring Boot Test framework
- Test both service and controller layers
- Mock repositories in service tests
- Use MockMvc for controller tests

## Game Mechanics

### Player System
- Players are created with customizable attributes:
  - **Background**: Character backstory
  - **Difficulty Level**: Easy, Medium, Hard
  - **Special Ability**: Unique player capability
  - **Starter Item**: Initial inventory item
- Player state tracked in MongoDB:
  - Status: PLAYING, PAUSED, WON, LOST
  - Current room location
  - Score and time remaining
  - Linked inventory (via inventoryId)

### Inventory System
- Each player has a linked inventory stored in MongoDB
- Players can:
  - View entire inventory
  - Add items (picked up from rooms)
  - Use items (with action types)
  - Inspect items

### Room System
- Rooms contain:
  - Interactive items
  - Puzzles to solve
  - Hints/clues
  - Lock status
- Players navigate between rooms
- Room data stored in MongoDB (static)

### Interaction System
- **GameObject** model represents interactive objects in rooms
- Interaction types:
  - INSPECT - Examine object
  - INTERACT - General interaction
  - PICK_UP - Add to inventory
  - OPEN - Open containers/doors
- Objects can have:
  - Multiple available actions
  - Clues (hints revealed on interaction)
  - Related objects (dependencies)
  - Room-specific placement

### Action System
- Core action types: inspect, use, move, open, close
- Actions processed through ActionService
- Actions can:
  - Trigger puzzles
  - Reveal clues
  - Change object states
  - Affect room locks

## Game Themes
The project supports multiple escape room themes:
- **Asylum** - Currently implemented (see `asylum` package)
  - Special features: AnnieRoom, StoryElements
  - Asylum-specific puzzles and narrative
- Additional themes can be added following the same pattern

## Important Notes
- This is **backend only** - no frontend code in this repository
- MongoDB stores both **static game data** (rooms, objects, items) and **dynamic state** (players, inventories)
- API design supports RESTful operations
- WebSocket support available for real-time features (future use)
- Player state persists in database
- Inventory is separate collection linked to players

## Build & Run
```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun

# Generate OpenAPI models
./gradlew openApiGenerate
```

## Common Tasks

### Adding a New Endpoint
1. Define in `specifications.yml`:
   - Add path under `paths:`
   - Define request/response schemas
   - Tag appropriately (Rooms, Items, Player, etc.)
2. Generate models: `./gradlew openApiGenerate`
3. Create controller method:
   - Use appropriate annotation (`@GetMapping`, `@PostMapping`)
   - Map to path defined in spec
   - Inject required services
4. Implement service logic:
   - Create method in service layer
   - Handle business logic
   - Call repositories as needed
5. Test the endpoint

### Adding New Static Game Data (Rooms, Objects, Items)
1. Create/update JSON in `src/main/resources/documentation/`:
   - `rooms.json` for new rooms
   - `game_objects.json` for interactive objects
   - `item.json` for items
2. Import to MongoDB:
   - Use MongoDB Compass or mongo shell
   - Import to appropriate collection
3. Verify model mapping:
   - Check `@Document(collection = "...")` annotation
   - Ensure fields match JSON structure
4. Create/update repository:
   - Extend `MongoRepository<Model, String>`
   - Add custom query methods if needed (e.g., `findByName`)
5. Use repository in service layer

### Adding a New Theme
1. Create package: `com.thefinaldoor.<theme-name>`
2. Follow asylum structure:
   ```
   <theme-name>/
     controllers/
     services/
     model/
     repositories/
     dto/
     constants/
   ```
3. Add theme-specific models:
   - Define `@Document` classes for collections
   - Create enums for theme-specific types
4. Implement services:
   - Theme-specific puzzle logic
   - Room navigation
   - Interaction handlers
5. Create controllers:
   - Map to theme-specific endpoints
   - Use core DTOs where possible
6. Add theme data to MongoDB:
   - Create theme-specific collections or use flags
   - Import JSON data

### Modifying Player State
1. Update `core.models.Player` if adding new fields
2. Modify `PlayerService` or `asylum.services.PlayerService`:
   - Add methods to update state
   - Save to repository
3. Update API if exposing to frontend:
   - Add to `specifications.yml`
   - Update PlayerController
4. Consider inventory linking if applicable

### Adding a New Action Type
1. Add to `ActionRequest` enum in `specifications.yml`:
   ```yaml
   enum:
     - inspect
     - use
     - move
     - open
     - close
     - <new-action>
   ```
2. Regenerate: `./gradlew openApiGenerate`
3. Add to `asylum.model.InteractionType` if theme-specific
4. Implement handler in `ActionService` or `InteractionService`:
   - Add switch case or if-else for new action
   - Define action logic
5. Update GameObject actions in MongoDB if needed

### Debugging Common Issues
- **MongoDB connection errors**: Check `application.yml` connection string
- **OpenAPI generation errors**: Clean and rebuild: `./gradlew clean openApiGenerate`
- **Null repository**: Ensure `@Repository` or proper Spring Data interface
- **Endpoint not found**: Check controller path mapping and server startup logs
- **CORS errors**: Configure CORS in Spring configuration (future frontend integration)

## Questions to Ask When Unsure
- "Is this a backend or frontend change?" (This is backend only - React frontend is separate)
- "Should this data be in MongoDB?" (Yes, for both static game data AND dynamic state)
- "Is this core or theme-specific?" (Core = reusable across themes; Theme = asylum-specific)
- "Does this need an API endpoint?" (If React frontend needs it, yes - add to specifications.yml)
- "Is there an existing pattern I should follow?" (Check similar controllers/services in core or asylum)
- "Which collection does this belong to?" (rooms, items, game objects, inventory, players)
- "Do I need to regenerate OpenAPI models?" (If specifications.yml changed, yes)
- "Should this be a service method or controller logic?" (Business logic → service; HTTP handling → controller)

---

**Last Updated**: December 11, 2024

