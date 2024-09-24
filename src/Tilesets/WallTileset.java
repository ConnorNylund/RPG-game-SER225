package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the WallTileset.png file
public class WallTileset extends Tileset {

    public WallTileset() {
        super(ImageLoader.load("WallTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // top of snow wall 
        Frame[] snowWallTopFrames = new Frame[] { 
                new FrameBuilder(getSubImage(0, 0), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(0, 1), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(0, 2), 20)
                        .withScale(tileScale)
                        .build(),
        };
        
        MapTileBuilder snowWallTopTile = new MapTileBuilder(snowWallTopFrames)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(snowWallTopTile);


        Frame[] snowWallMiddleFrames = new Frame[] {
                new FrameBuilder(getSubImage(1, 0), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(1, 1), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 2), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder snowWallMiddleTile = new MapTileBuilder(snowWallMiddleFrames)
        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(snowWallMiddleTile);


        Frame[] snowWallBottomFrames = new Frame[] {
                new FrameBuilder(getSubImage(2, 0), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(2, 1), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 2), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder snowWallBottomTile = new MapTileBuilder(snowWallBottomFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(snowWallBottomTile);


        Frame[] LavaWallTopFrames = new Frame[] {
                new FrameBuilder(getSubImage(3, 0), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(3, 1), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(3, 2), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder LavaWallTopTile = new MapTileBuilder(LavaWallTopFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(LavaWallTopTile);


        Frame[] LavaWallMiddleFrames = new Frame[] {
                new FrameBuilder(getSubImage(4, 0), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(4, 1), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(4, 2), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder LavaWallMiddleTile = new MapTileBuilder(LavaWallMiddleFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(LavaWallMiddleTile);


        Frame[] LavaWallBottomFrames = new Frame[] {
                new FrameBuilder(getSubImage(5, 0), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(5, 1), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(5, 2), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder LavaWallBottomTile = new MapTileBuilder(LavaWallBottomFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(LavaWallBottomTile);


        Frame[] BrickWallTopFrames = new Frame[] {
                new FrameBuilder(getSubImage(6, 0), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(6, 1), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(6, 2), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder BrickWallTopTile = new MapTileBuilder(BrickWallTopFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(BrickWallTopTile);


        Frame[] BrickWallMiddleFrames = new Frame[] {
                new FrameBuilder(getSubImage(7, 0), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(7, 1), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(7, 2), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder BrickWallMiddleTile = new MapTileBuilder(BrickWallMiddleFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(BrickWallMiddleTile);


        Frame[] brickWallBottomFrames = new Frame[] {
                new FrameBuilder(getSubImage(8, 0), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(8, 1), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(8, 2), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder brickWallBottomTile = new MapTileBuilder(brickWallBottomFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(brickWallBottomTile);

        return mapTiles;
    }
}
