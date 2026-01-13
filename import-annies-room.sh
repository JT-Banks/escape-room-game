#!/bin/bash
# MongoDB Import Script for Annie's Room (Linux/Mac)
# Replace YOUR_MONGODB_URI with your actual connection string

MONGODB_URI="YOUR_MONGODB_URI"
DB_NAME="Game"

echo "🎮 Importing Annie's Room data to MongoDB..."

# Import Game Objects
echo ""
echo "📦 Importing game objects..."
mongoimport --uri "$MONGODB_URI" --db "$DB_NAME" --collection "game objects" --file "src/main/resources/documentation/game_objects.json" --jsonArray --drop

# Import Items
echo ""
echo "🎒 Importing items..."
mongoimport --uri "$MONGODB_URI" --db "$DB_NAME" --collection "items" --file "src/main/resources/documentation/item.json" --jsonArray --drop

# Import Rooms
echo ""
echo "🚪 Importing rooms..."
mongoimport --uri "$MONGODB_URI" --db "$DB_NAME" --collection "rooms" --file "src/main/resources/documentation/rooms.json" --jsonArray --drop

echo ""
echo "✅ Import complete!"
echo "Collections updated:"
echo "  - game objects (8 Annie's Room objects)"
echo "  - items (14 items including Annie's letters)"
echo "  - rooms (Annie's Room + Bathroom/Janitor's Closet)"

