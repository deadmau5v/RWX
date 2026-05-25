package android.opengl;

import android.view.View;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;

public class GLSurfaceView extends View {
    public interface Renderer {
        void onSurfaceCreated(GL10 gl, EGLConfig config);
        void onSurfaceChanged(GL10 gl, int width, int height);
        void onDrawFrame(GL10 gl);
    }
}
