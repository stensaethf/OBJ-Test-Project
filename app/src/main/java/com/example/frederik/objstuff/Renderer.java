package com.example.frederik.objstuff;

/**
 * Created by Frederik on 2/7/17.
 */
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import org.rajawali3d.Object3D;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.loader.ParsingException;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;
import org.rajawali3d.renderer.RajawaliRenderer;

public class Renderer extends RajawaliRenderer {

    public Context context;
    private DirectionalLight directionalLight;
    private Sphere earthSphere;
    private Object3D multiobj;

    public Renderer(Context context) {
        super(context);
        this.context = context;
        setFrameRate(60);
    }

    public void initScene(){
        directionalLight = new DirectionalLight(1f, .2f, -1.0f);
        directionalLight.setColor(1.0f, 1.0f, 1.0f);
        directionalLight.setPower(2);
        getCurrentScene().addLight(directionalLight);

        Material material = new Material();
        material.enableLighting(true);
        material.setDiffuseMethod(new DiffuseMethod.Lambert());
        material.setColor(0);

//        Texture earthTexture = new Texture("Earth", R.drawable.earthtruecolor_nasa_big);
//        try{
//            material.addTexture(earthTexture);
//
//        } catch (ATexture.TextureException error){
//            Log.d("DEBUG", "TEXTURE ERROR");
//        }
//
//        earthSphere = new Sphere(1, 24, 24);
//        earthSphere.setMaterial(material);
//        getCurrentScene().addChild(earthSphere);
        getCurrentCamera().setZ(15f);

        LoaderOBJ parser = new LoaderOBJ(getContext().getResources(), mTextureManager,
                R.raw.multiobjects_obj);
        try {
            parser.parse();
        } catch (ParsingException e) {
            e.printStackTrace();
        }
        multiobj = parser.getParsedObject();
        multiobj.setMaterial(material);
        getCurrentScene().addChild(multiobj);

//        ObjParser objParser = new ObjParser(mContext.getResources(), mTextureManager, R.raw.tal_obj);
//        objParser.parse();
//        BaseObject3D mObject = objParser.getParsedObject();
//        mObject.setLight(mLight);
//        addChild(mObject);
    }

    @Override
    public void onRender(final long elapsedTime, final double deltaTime) {
        super.onRender(elapsedTime, deltaTime);
//        earthSphere.rotate(Vector3.Axis.Y, 1.0);
        multiobj.rotate(Vector3.Axis.Y, 1.0);
    }

    public void onTouchEvent(MotionEvent event){
    }

    public void onOffsetsChanged(float x, float y, float z, float w, int i, int j){
    }
}

