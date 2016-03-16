/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package worldserver3d;

import com.jme.bounding.BoundingBox;
import com.jme.image.Texture;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.util.export.binary.BinaryImporter;
import com.jmex.model.converters.MaxToJme;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import com.jme.scene.shape.Arrow;
import com.jme.scene.Node;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.shape.Pyramid;
import model.Material3D;
import com.jme.scene.state.MaterialState;
import com.jme.scene.state.TextureState;
import com.jme.util.TextureManager;
import model.Environment;

/**
 *
 * @author eccastro
 */
public class IconFactory {
    ByteArrayOutputStream BO;
    public double x, y;
    private float previousZ;
    //z= 0;
    public String myModelName;
    public  Vector3f transVector;
    public Material3D material;
    public MaterialState ms;
    private TextureState ts;
    private int envWidth, envHeight;

    public IconFactory(MaterialState ms, int width, int height) {
        this.ms = ms;
        envWidth = width;
        envHeight = height;

    }

    public IconFactory(TextureState ts, double x, double y, int width, int height) {
        this.ts = ts;
        this.x = x;
        this.y = y;
        envWidth = width;
        envHeight = height;
    }
    //Used to place an arrow in the center of hidden brick. This is currently
    // used instead of getRememberMeIconNode.
     public Node getRememberMeIcon(MaterialState ms, double x1x2, double y1y2){
         Vector3f v = new Vector3f((float)(x1x2/20-envWidth/20), 0.f, (float)((y1y2/20)-envHeight/20));
         x = v.getX();
         y = v.getZ();
         material = new Material3D(ColorRGBA.white, 0.0, 0.0, ms);
         this.ms = ms;
         Arrow a = new Arrow("Arrow", 7, 0.2f);
         //System.out.println("=======Arrow length= "+ a.getLength()+" width= "+a.getWidth() );
         a.setLocalTranslation(v);
         Node model = null;
         myModelName = "ModelRememberMeIcon_"+x+"_"+y;
         model = new Node(myModelName);
         model.attachChild(a);
         model.setModelBound(new BoundingBox());
         model.updateModelBound();
         return model;

     }


    public Node getWaypointIcon(){
        Vector3f v = new Vector3f((float)(this.x/10-envWidth/20), 0.f, (float)((this.y/10)-envHeight/20));

         Arrow a = new Arrow("Arrow", 7, 0.2f);
         setTexture("images/green_metalic.jpg");
         a.setLocalTranslation(v);
         a.setRenderState(ts);
         Node model = null;
         myModelName = "ModelWaypointIcon_"+this.x+"_"+this.y;
         model = new Node(myModelName);
         model.attachChild(a);
         model.setModelBound(new BoundingBox());
         model.updateModelBound();
         return model;

     }
    public Node getDeliverySpot(){
        Vector3f v = new Vector3f((float)(this.x/10-envWidth/20), 5f, (float)((this.y/10)-envHeight/20));

         Pyramid a = new Pyramid("Pyramid", 6, 10);
         setTexture("images/bricks_texture.jpg");
         a.setLocalTranslation(v);
         a.setRenderState(ts);
         Node model = null;
         myModelName = "ModelDeliverySpotIcon_"+this.x+"_"+this.y;
         model = new Node(myModelName);
         model.attachChild(a);
         model.setModelBound(new BoundingBox());
         model.updateModelBound();
         return model;

     }

    public void setTexture(String pathToImageTexture){
         this.ts.setTexture(TextureManager.loadTexture(IconFactory.class.getClassLoader().getResource(
                pathToImageTexture),
                Texture.MinificationFilter.Trilinear,
                Texture.MagnificationFilter.Bilinear));
         this.ts.setEnabled(true);

    }


    // Discontinued.
    public Node getRememberMeIconNode() {
        System.out.println("======= getRememberMeIconNode   =======");
        Node modelw=null;
        try {ByteArrayInputStream RememberMeIconInputStream = new ByteArrayInputStream(BO.toByteArray());             
             modelw = (Node)BinaryImporter.getInstance().load(RememberMeIconInputStream);
               } catch (IOException exc) {
                     System.out.println("Erro !");
               }  
        modelw.setLocalScale(0.02f);
        Quaternion quat90 = new Quaternion();
        
        quat90.fromAngles(270 * 3.141592f/180, 0f, 0f);
        modelw.setLocalRotation(quat90);
       
        modelw.setLocalTranslation(transVector);
        Node model = null;
        myModelName = "ModelRememberMeIcon_"+x+"_"+y;
        model = new Node(myModelName);
        model.attachChild(modelw);
        model.setModelBound(new BoundingBox());
        model.updateModelBound();
        return(model);
    }

    
}
