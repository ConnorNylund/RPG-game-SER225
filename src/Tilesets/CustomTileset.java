// package Tilesets;

// import java.awt.image.BufferedImage;
// import java.util.ArrayList;

// import Builders.FrameBuilder;
// import Builders.MapTileBuilder;
// import Engine.ImageLoader;
// import GameObject.Frame;
// import Level.Tileset;

// public class CustomTileset extends Tileset {

//     public CustomTileset() {
//         super(ImageLoader.load("CustomTileset.png"),16,16,3);
//         //TODO Auto-generated constructor stub
//     }

//     @Override
//     public ArrayList<MapTileBuilder> defineTiles() {

//     //Framework to add a tile to the screen by 
//         ArrayList<MapTileBuilder> mapTiles = new ArrayList<>(); 
//         // TODO Auto-generated method stub
//         Frame exampleFrame = new FrameBuilder(getSubImage(0,0))
//             .withScale(tileScale)
//             .build(); 

//         MapTileBuilder exampleTile = new MapTileBuilder(exampleFrame);
//         mapTiles.add(exampleTile); 




//         return mapTiles; 
//     }
// }
