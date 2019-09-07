/*
 * Copyright (c) 2009-2012 ShaderBlow
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'ShaderBlow' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jme3.shaderblow.filter.circularfading;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.post.Filter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;

/**
 * <pre>
 * Set speed to 0 ({@link CircularFadingFilter#setFadingSpeed(0)}) to avoid fading animation.
 * </pre>
 * 
 * @author H
 */
public class CircularFadingFilter extends Filter {

    /** Material definition path */
    private static final String MAT_DEFS = "MatDefs/Filters/CircularFading/CircularFading.j3md";

    /** Material's parameters */
    private static final String MP_CIRCLE_RADIUS = "CircleRadius";
    private static final String MP_CIRCLE_CENTER = "CircleCenter";

    /** Fading animation general speed */
    private static final float FADING_SPEED = 0.5f;

    private static final float DEFAULT_CIRCLE_RADIUS = 2.0f;

    private final Camera camera;

    /** Geometry's position use as Circle's center point */
    private Vector3f circleCenter;

    /** Circle's radius */
    private float circleRadius = DEFAULT_CIRCLE_RADIUS;

    /** Fading animation speed */
    private float fadingSpeed = 1.0f;

    /**
     * Constructor.
     * 
     * @param camera
     */
    public CircularFadingFilter(final Camera camera, final Vector3f target) {
        super("CircularFadingFilter");
        this.camera = camera;
        this.circleCenter = target;
    }

    /**
     * @see Filter#getMaterial()
     */
    @Override
    protected Material getMaterial() {
        return this.material;
    }

    /**
     * @see Filter#initFilter(AssetManager, RenderManager,
     *      ViewPort, int, int)
     */
    @Override
    protected void initFilter(final AssetManager manager, final RenderManager renderManager, final ViewPort vp,
            final int w, final int h) {
        this.material = new Material(manager, MAT_DEFS);
        this.material.setVector3(MP_CIRCLE_CENTER, this.camera.getScreenCoordinates(this.circleCenter));
        this.material.setFloat(MP_CIRCLE_RADIUS, this.circleRadius);
    }

    @Override
    protected void preFrame(final float tpf) {
        this.circleRadius -= tpf * this.fadingSpeed * FADING_SPEED;
        this.material.setFloat(MP_CIRCLE_RADIUS, this.circleRadius);

        this.material.setVector3(MP_CIRCLE_CENTER, this.camera.getScreenCoordinates(this.circleCenter));
    }

    /**
     * <pre>
     * Set the circle's radius to {@link CircularFadingFilter#DEFAULT_CIRCLE_RADIUS}.
     * NOTE: FadingSpeed is not reset.
     * </pre>
     */
    public void reset() {
        this.circleRadius = DEFAULT_CIRCLE_RADIUS;
    }

    public Vector3f getCircleCenter() {
        return this.circleCenter;
    }

    public void setTargetPosition(final Vector3f target) {
        this.circleCenter = target;
    }

    public void setCircleRadius(final float circleRadius) {
        this.circleRadius = circleRadius;
    }

    public void setFadingSpeed(final float fadingSpeed) {
        this.fadingSpeed = fadingSpeed;
    }
}
