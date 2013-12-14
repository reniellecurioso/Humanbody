package lspu.it.humanbody;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import rajawali.Object3D;
import rajawali.animation.Animation3D;
import rajawali.animation.Animation3D.RepeatMode;
import rajawali.animation.EllipticalOrbitAnimation3D;
import rajawali.animation.EllipticalOrbitAnimation3D.OrbitDirection;
import rajawali.animation.RotateAnimation3D;
import rajawali.lights.DirectionalLight;
import rajawali.lights.PointLight;
import rajawali.materials.Material;
import rajawali.materials.textures.ATexture.TextureException;
import rajawali.materials.textures.Texture;
import rajawali.math.vector.Vector3;
import rajawali.math.vector.Vector3.Axis;
import rajawali.parser.LoaderOBJ;
import rajawali.parser.ParsingException;
import rajawali.primitives.Sphere;
import rajawali.renderer.RajawaliRenderer;

public class HumanbodyRenderer extends RajawaliRenderer {

	private PointLight mLight;
	private Object3D mBody;
	private Animation3D mCameraA, mLightA;
	
	public HumanbodyRenderer(Context context) {
		super(context);
		setFrameRate(60);
	}

	public void initScene() {
		mLight = new PointLight();
		mLight.setPosition(0,0,5);
		mLight.setPower(3);
		getCurrentScene().addLight(mLight);
		getCurrentCamera().setZ(16);
		LoaderOBJ objParser = new LoaderOBJ(mContext.getResources(),mTextureManager,R.raw.tao_obj);
		
		try {
			objParser.parse();
			mBody = objParser.getParsedObject();
			addChild(mBody);
			mCameraA = new RotateAnimation3D(Axis.Y,360);
			mCameraA.setDuration(8000);
			mCameraA.setRepeatMode(RepeatMode.INFINITE);
			mCameraA.setTransformable3D(mBody);
			
			
		} catch (ParsingException e) {
			e.printStackTrace();
		}
		
		mLightA = new EllipticalOrbitAnimation3D(new Vector3(), 
				new Vector3(0,10,0), Vector3.getAxisVector(Axis.Z),0,360,OrbitDirection.CLOCKWISE);
		mLightA.setDuration(3000);
		mLightA.setRepeatMode(RepeatMode.INFINITE);
		mLightA.setTransformable3D(mLight);
		
		
		registerAnimation(mCameraA);
		registerAnimation(mLightA);
		mCameraA.play();
		mLightA.play();
		
	}
	
	
}
