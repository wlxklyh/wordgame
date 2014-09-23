/*
 * 创建日期 2013-5-11
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package smallgameengine.image;

import javax.microedition.khronos.opengles.GL10;

public class TouchBigButton extends Image {

	public float w2,h2;
	public TouchBigButton(GL10 gl, int textureId, float x, float y, float z,
			float w, float h,float w2, float h2) {
		super(gl, textureId, x, y, z, w, h);
		this.w2=w2;
		this.h2=h2;
		// TODO 自动生成构造函数存根
	}
}
