package com.foti.compass

import android.content.Context
import android.graphics.*
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener2
import android.hardware.SensorManager
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.withMatrix

class CompassView(context: Context?) : View(context), SensorEventListener2 {

    private var mLastAcceleration = FloatArray(3)
    private var mLastMagnetic = FloatArray(3)
    private var mLastOrientation = FloatArray(3)
    private var mRotationMatrix = FloatArray(9)

    private var yaw = 45f

    private val arrow = floatArrayOf(-0.5f, 0f, 0.5f, 0f, 0f, 1f)

    private lateinit var compass : Bitmap

    private var CM = Matrix()
    private var AM = Matrix()

    private var converted = false

    init {
        val sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL)
        //setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        //setLayerType(View.LAYER_TYPE_HARDWARE, null)
        //setLayerType(View.LAYER_TYPE_NONE, null)
    }

    val mPaint = Paint().also {

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val verts = floatArrayOf(0f, 0f, 500f, 0f,  0f, 500f)
        canvas.drawVertices(Canvas.VertexMode.TRIANGLES, verts.size, verts, 0,null,
                0, intArrayOf(Color.RED, Color.GREEN, Color.BLUE), 0, null,
                0, 0,mPaint)


        if(!converted){
            compass = ResourcesCompat.getDrawable(resources, R.drawable.compass, null)?.toBitmap(width, width)!!
            converted=true
        }
        CM.setTranslate(0f, (height - width)/2f)
        CM.postScale(0.6f, 0.6f, width/2f, height/2f)
        CM.postRotate(-yaw*180/Math.PI.toFloat(), width/2f, height/2f)
        canvas.withMatrix(CM) {
            drawBitmap(compass, 0f, 0f, null)
        }

        AM.setScale(-100f, 150f)
        AM.postTranslate(width/2f, height/4f)

        canvas.withMatrix(AM) {
            drawVertices(Canvas.VertexMode.TRIANGLES, arrow.size, arrow, 0,null, 0, intArrayOf(Color.BLUE, Color.BLUE, Color.BLUE), 0, null, 0, 0,mPaint)
        }





    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            mLastAcceleration = event?.values.clone()
        }
        if (event?.sensor?.type == Sensor.TYPE_MAGNETIC_FIELD) {
            mLastMagnetic = event?.values.clone()
        }

        SensorManager.getRotationMatrix(mRotationMatrix, null, mLastAcceleration, mLastMagnetic)
        SensorManager.getOrientation(mRotationMatrix, mLastOrientation)

        yaw = mLastOrientation[0]

        invalidate()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.i("info", "Not yet implemented")
    }

    override fun onFlushCompleted(sensor: Sensor?) {
        Log.i("info", "Not yet implemented")
    }
}