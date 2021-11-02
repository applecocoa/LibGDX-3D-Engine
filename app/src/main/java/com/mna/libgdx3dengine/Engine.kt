package com.mna.libgdx3dengine

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.VertexAttributes
import com.badlogic.gdx.graphics.g3d.*
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight

class Engine : ApplicationListener {

    // MARK: - Properties -

    var environment : Environment? = null
    var camera : PerspectiveCamera? = null
    var cameraController : CameraInputController? = null
    var modelBatch : ModelBatch? = null
    var modelA : Model? = null
    var modelInstanceA : ModelInstance? = null
    var modelB : Model? = null
    var modelInstanceB : ModelInstance? = null

    // MARK: - Init -

    override fun create() {

        // environment

        environment = Environment()
        environment!!.set(ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f))
        environment!!.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f))
        modelBatch = ModelBatch()

        // camera

        camera = PerspectiveCamera(
            67f, Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat()
        )
        camera!!.position[10f, 10f] = 10f
        camera!!.lookAt(0f, 0f, 0f)
        camera!!.near = 1f
        camera!!.far = 300f
        camera!!.update()

        // modelBuilder

        val modelBuilder = ModelBuilder()

        // modelA - box

        modelA = modelBuilder.createBox(
            5f, 5f, 5f,
            Material(ColorAttribute.createDiffuse(Color.GREEN)),
            (VertexAttributes.Usage.Position or VertexAttributes.Usage.Normal).toLong()
        )

        modelInstanceA = ModelInstance(modelA)

        // position the model from point (0, 0, 0)

        modelInstanceA!!.transform.translate(3f, 0f, 3f)

        // modelB - sphere

        modelB = modelBuilder.createSphere(3f, 3f, 3f,
            30, 30,
            Material(ColorAttribute.createDiffuse(Color.BLUE)),
            (VertexAttributes.Usage.Position or VertexAttributes.Usage.Normal).toLong())

        modelInstanceB = ModelInstance(modelB)

        // position the model from point (0, 0, 0)

        modelInstanceB!!.transform.translate(-3f, 0f, -3f)

        // cameraController

        cameraController = CameraInputController(camera)

        Gdx.input.inputProcessor = cameraController

    }

    // MARK: - Render -

    override fun render() {

        // update camera controller

        cameraController!!.update()

        // clear bitmap

        Gdx.gl.glViewport(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)

        // begin render

        modelBatch!!.begin(camera)

        // render instances

        modelBatch!!.render(modelInstanceA, environment)
        modelBatch!!.render(modelInstanceB, environment)

        // end render

        modelBatch!!.end()

    }

    // MARK: - Dispose -

    override fun dispose() {

        // garbage collection

        modelBatch!!.dispose()
        modelA?.dispose()
        modelB?.dispose()

    }

    // MARK: - Resize View -

    override fun resize(width: Int, height: Int) {

        //

    }

    // MARK: - Pause -

    override fun pause() {

        //

    }

    // MARK: - Resume -

    override fun resume() {

        //

    }

}