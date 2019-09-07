package com.jme3.shaderblow.test.bubble;

import com.jme3.app.SimpleApplication;
import com.jme3.input.ChaseCamera;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.util.SkyFactory;

public class Bubble extends SimpleApplication {
    
    public static void main(String[] args) {
        Bubble app = new Bubble();
        app.start();
    }
    

    @Override
    public void simpleInitApp() {
        Sphere b = new Sphere(200, 200, 1);
        
        Geometry geom = new Geometry("Sphere", b);

        Material mat = new Material(assetManager,"MatDefs/Bubble/Bubble.j3md"); //"Common/MatDefs/Misc/Unshaded.j3md");//"MatDefs/bubbleMat.j3md");
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/Bubble/rainbow.png"));
        mat.setFloat("Shininess", 20f);
        mat.setColor("SpecularColor", ColorRGBA.Blue);
        mat.setBoolean("UseSpecularNormal", true);
       
        geom.setMaterial(mat);
        geom.setQueueBucket(RenderQueue.Bucket.Transparent);

        rootNode.attachChild(geom);
        rootNode.attachChild(SkyFactory.createSky(
            assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
        
        flyCam.setEnabled(false);
        ChaseCamera chaseCam = new ChaseCamera(cam, geom, inputManager);
        
       
    }
    
    
    @Override
    public void simpleUpdate(float tpf) {
        
    }
       
}

