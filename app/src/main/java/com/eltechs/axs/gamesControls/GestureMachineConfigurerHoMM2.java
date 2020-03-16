package com.eltechs.axs.gamesControls;

import com.eltechs.axs.GestureStateMachine.GestureContext;
import com.eltechs.axs.GestureStateMachine.GestureState1FingerMeasureSpeed;
import com.eltechs.axs.GestureStateMachine.GestureState1FingerMoveToMouseMove;
import com.eltechs.axs.GestureStateMachine.GestureState1FingerMoveToScrollSync;
import com.eltechs.axs.GestureStateMachine.GestureState1FingerToLongClick;
import com.eltechs.axs.GestureStateMachine.GestureState1FingerToZoomMove;
import com.eltechs.axs.GestureStateMachine.GestureState2FingersToZoom;
import com.eltechs.axs.GestureStateMachine.GestureStateCheckIfZoomed;
import com.eltechs.axs.GestureStateMachine.GestureStateClickToFingerFirstCoords;
import com.eltechs.axs.GestureStateMachine.GestureStateMouseWarpToFingerLastCoords;
import com.eltechs.axs.GestureStateMachine.GestureStateNeutral;
import com.eltechs.axs.GestureStateMachine.GestureStatePressKey;
import com.eltechs.axs.GestureStateMachine.GestureStateWaitFingersNumberChangeWithTimeout;
import com.eltechs.axs.GestureStateMachine.GestureStateWaitForNeutral;
import com.eltechs.axs.GestureStateMachine.PointerContext;
import com.eltechs.axs.GuestAppActionAdapters.AlignedMouseClickAdapter;
import com.eltechs.axs.GuestAppActionAdapters.MouseClickAdapterWithCheckPlacementContext;
import com.eltechs.axs.GuestAppActionAdapters.OffsetMouseMoveAdapter;
import com.eltechs.axs.GuestAppActionAdapters.PressAndHoldMouseClickAdapter;
import com.eltechs.axs.GuestAppActionAdapters.PressAndReleaseMouseClickAdapter;
import com.eltechs.axs.GuestAppActionAdapters.PressAndReleaseWithModifierKeyMouseClickAdapter;
import com.eltechs.axs.GuestAppActionAdapters.ScrollAdapterControlArrow;
import com.eltechs.axs.GuestAppActionAdapters.SimpleMouseMoveAdapter;
import com.eltechs.axs.GuestAppActionAdapters.SimpleMousePointAndClickAdapter;
import com.eltechs.axs.KeyCodesX;
import com.eltechs.axs.TouchArea;
import com.eltechs.axs.TouchEventMultiplexor;
import com.eltechs.axs.finiteStateMachine.FiniteStateMachine;
import com.eltechs.axs.finiteStateMachine.generalStates.FSMStateRunRunnable;
import com.eltechs.axs.widgets.viewOfXServer.TransformationHelpers;
import com.eltechs.axs.widgets.viewOfXServer.ViewOfXServer;

public class GestureMachineConfigurerHoMM2 {
    private static final float clickAlignThresholdInches = 0.3f;
    private static final float doubleClickMaxDistance = 0.15f;
    private static final int doubleClickMaxIntervalMs = 75;
    private static final float fingerAimingMaxMoveInches = 0.6f;
    private static final int fingerSpeedCheckTimeMs = 400;
    private static final float fingerStandingMaxMoveInches = 0.12f;
    private static final float fingerTapMaxMoveInches = 0.2f;
    private static final int fingerTapMaxTimeMs = 400;
    private static final int maxTapTimeMs = 100;
    private static final int mouseActionSleepMs = 50;
    private static final float pixelsInScrollUnit = 66.0f;
    private static final int pointerMarginXPixels = 50;
    private static final float pointerOffsetAimInchesX = 0.0f;
    private static final float pointerOffsetAimInchesY = -0.3f;
    private static final long scrollPeriodMs = 150;

    public static GestureContext createGestureContext(ViewOfXServer viewOfXServer, TouchArea touchArea, TouchEventMultiplexor touchEventMultiplexor, int i, Runnable runnable) {
        ViewOfXServer viewOfXServer2 = viewOfXServer;
        GestureContext gestureContext = new GestureContext(viewOfXServer2, touchArea, touchEventMultiplexor);
        PointerContext pointerContext = new PointerContext();
        GestureStateNeutral gestureStateNeutral = new GestureStateNeutral(gestureContext);
        GestureStateWaitForNeutral gestureStateWaitForNeutral = new GestureStateWaitForNeutral(gestureContext);
        float f = (float) i;
        GestureState1FingerMeasureSpeed gestureState1FingerMeasureSpeed = new GestureState1FingerMeasureSpeed(gestureContext, 400, fingerStandingMaxMoveInches * f, fingerAimingMaxMoveInches * f, fingerTapMaxMoveInches * f, 400.0f);
        GestureStateCheckIfZoomed gestureStateCheckIfZoomed = new GestureStateCheckIfZoomed(gestureContext);
        GestureStateMouseWarpToFingerLastCoords gestureStateMouseWarpToFingerLastCoords = new GestureStateMouseWarpToFingerLastCoords(gestureContext, new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), pointerContext);
        SimpleMousePointAndClickAdapter simpleMousePointAndClickAdapter = new SimpleMousePointAndClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50), pointerContext);
        SimpleMousePointAndClickAdapter simpleMousePointAndClickAdapter2 = simpleMousePointAndClickAdapter;
        float f2 = clickAlignThresholdInches * f;
        ViewOfXServer viewOfXServer3 = viewOfXServer2;
        PointerContext pointerContext2 = pointerContext;
        AlignedMouseClickAdapter alignedMouseClickAdapter = new AlignedMouseClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50), viewOfXServer3, pointerContext2, f2);
        SimpleMouseMoveAdapter simpleMouseMoveAdapter = new SimpleMouseMoveAdapter(gestureContext.getPointerReporter());
        PressAndReleaseMouseClickAdapter pressAndReleaseMouseClickAdapter = new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50);
        PressAndReleaseWithModifierKeyMouseClickAdapter pressAndReleaseWithModifierKeyMouseClickAdapter = new PressAndReleaseWithModifierKeyMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50, gestureContext.getKeyboardReporter(), KeyCodesX.KEY_SHIFT_LEFT);
        AlignedMouseClickAdapter alignedMouseClickAdapter2 = new AlignedMouseClickAdapter(simpleMouseMoveAdapter, pressAndReleaseMouseClickAdapter, pressAndReleaseWithModifierKeyMouseClickAdapter, viewOfXServer3, pointerContext2, 0.15f * f);
        MouseClickAdapterWithCheckPlacementContext mouseClickAdapterWithCheckPlacementContext = new MouseClickAdapterWithCheckPlacementContext(simpleMousePointAndClickAdapter2, alignedMouseClickAdapter, alignedMouseClickAdapter2, pointerContext, 75);
        GestureStateClickToFingerFirstCoords gestureStateClickToFingerFirstCoords = new GestureStateClickToFingerFirstCoords(gestureContext, mouseClickAdapterWithCheckPlacementContext);
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout = new GestureStateWaitFingersNumberChangeWithTimeout(gestureContext, 100);
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout2 = new GestureStateWaitFingersNumberChangeWithTimeout(gestureContext, 100);
        FSMStateRunRunnable fSMStateRunRunnable = new FSMStateRunRunnable(runnable);
        GestureStatePressKey gestureStatePressKey = new GestureStatePressKey(gestureContext, KeyCodesX.KEY_SPACE);
        GestureStatePressKey gestureStatePressKey2 = new GestureStatePressKey(gestureContext, KeyCodesX.KEY_RETURN);
        GestureState1FingerMoveToMouseMove gestureState1FingerMoveToMouseMove = new GestureState1FingerMoveToMouseMove(gestureContext, pointerContext, new OffsetMouseMoveAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), 0.0f * f, f * pointerOffsetAimInchesY));
        GestureStatePressKey gestureStatePressKey3 = gestureStatePressKey;
        GestureStateClickToFingerFirstCoords gestureStateClickToFingerFirstCoords2 = gestureStateClickToFingerFirstCoords;
        SimpleMousePointAndClickAdapter simpleMousePointAndClickAdapter3 = new SimpleMousePointAndClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndHoldMouseClickAdapter(gestureContext.getPointerReporter(), 3), pointerContext);
        GestureState1FingerMoveToMouseMove gestureState1FingerMoveToMouseMove2 = gestureState1FingerMoveToMouseMove;
        GestureStatePressKey gestureStatePressKey4 = gestureStatePressKey2;
        GestureStatePressKey gestureStatePressKey5 = gestureStatePressKey3;
        FSMStateRunRunnable fSMStateRunRunnable2 = fSMStateRunRunnable;
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout3 = gestureStateWaitFingersNumberChangeWithTimeout2;
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout4 = gestureStateWaitFingersNumberChangeWithTimeout;
        AlignedMouseClickAdapter alignedMouseClickAdapter3 = new AlignedMouseClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndHoldMouseClickAdapter(gestureContext.getPointerReporter(), 3), new PressAndHoldMouseClickAdapter(gestureContext.getPointerReporter(), 3), viewOfXServer2, pointerContext, f2);
        MouseClickAdapterWithCheckPlacementContext mouseClickAdapterWithCheckPlacementContext2 = new MouseClickAdapterWithCheckPlacementContext(simpleMousePointAndClickAdapter3, alignedMouseClickAdapter3, new SimpleMousePointAndClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndHoldMouseClickAdapter(gestureContext.getPointerReporter(), 3), pointerContext), pointerContext, 75);
        GestureState1FingerToLongClick gestureState1FingerToLongClick = new GestureState1FingerToLongClick(gestureContext, mouseClickAdapterWithCheckPlacementContext2);
        GestureState1FingerToLongClick gestureState1FingerToLongClick2 = gestureState1FingerToLongClick;
        GestureState1FingerMoveToMouseMove gestureState1FingerMoveToMouseMove3 = gestureState1FingerMoveToMouseMove2;
        GestureStateWaitForNeutral gestureStateWaitForNeutral2 = gestureStateWaitForNeutral;
        GestureStateCheckIfZoomed gestureStateCheckIfZoomed2 = gestureStateCheckIfZoomed;
        GestureStateMouseWarpToFingerLastCoords gestureStateMouseWarpToFingerLastCoords2 = gestureStateMouseWarpToFingerLastCoords;
        GestureStateClickToFingerFirstCoords gestureStateClickToFingerFirstCoords3 = gestureStateClickToFingerFirstCoords2;
        GestureState1FingerMoveToScrollSync gestureState1FingerMoveToScrollSync = new GestureState1FingerMoveToScrollSync(gestureContext, new ScrollAdapterControlArrow(gestureContext.getKeyboardReporter()), 0.015151516f * TransformationHelpers.getScaleX(viewOfXServer.getViewToXServerTransformationMatrix()), 0.015151516f * TransformationHelpers.getScaleY(viewOfXServer.getViewToXServerTransformationMatrix()), 0.0f, true, 50, scrollPeriodMs, false);
        GestureState1FingerToZoomMove gestureState1FingerToZoomMove = new GestureState1FingerToZoomMove(gestureContext);
        GestureState2FingersToZoom gestureState2FingersToZoom = new GestureState2FingersToZoom(gestureContext);
        FiniteStateMachine finiteStateMachine = new FiniteStateMachine();
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout5 = gestureStateWaitFingersNumberChangeWithTimeout4;
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout6 = gestureStateWaitFingersNumberChangeWithTimeout3;
        FSMStateRunRunnable fSMStateRunRunnable3 = fSMStateRunRunnable2;
        GestureStatePressKey gestureStatePressKey6 = gestureStatePressKey5;
        GestureStatePressKey gestureStatePressKey7 = gestureStatePressKey4;
        GestureState1FingerMoveToMouseMove gestureState1FingerMoveToMouseMove4 = gestureState1FingerMoveToMouseMove3;
        GestureContext gestureContext2 = gestureContext;
        GestureState1FingerToLongClick gestureState1FingerToLongClick3 = gestureState1FingerToLongClick2;
        FSMStateRunRunnable fSMStateRunRunnable4 = fSMStateRunRunnable3;
        GestureState1FingerMoveToScrollSync gestureState1FingerMoveToScrollSync3 = gestureState1FingerMoveToScrollSync;
        finiteStateMachine.setStatesList(gestureStateNeutral, gestureState1FingerMeasureSpeed, gestureStateMouseWarpToFingerLastCoords2, gestureStateClickToFingerFirstCoords3, gestureStateCheckIfZoomed2, gestureStateWaitFingersNumberChangeWithTimeout5, gestureStateWaitFingersNumberChangeWithTimeout6, fSMStateRunRunnable3, gestureStatePressKey6, gestureStatePressKey7, gestureState1FingerMoveToMouseMove4, gestureState1FingerToLongClick3, gestureState1FingerMoveToScrollSync3, gestureState1FingerToZoomMove, gestureState2FingersToZoom, gestureStateWaitForNeutral2);
        GestureStateWaitForNeutral gestureStateWaitForNeutral3 = gestureStateWaitForNeutral2;
        finiteStateMachine.addTransition(gestureStateWaitForNeutral3, GestureStateWaitForNeutral.GESTURE_COMPLETED, gestureStateNeutral);
        finiteStateMachine.addTransition(gestureStateNeutral, GestureStateNeutral.FINGER_TOUCHED, gestureState1FingerMeasureSpeed);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_STANDING, gestureState1FingerToLongClick3);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_WALKED, gestureState1FingerMoveToMouseMove4);
        GestureStateCheckIfZoomed gestureStateCheckIfZoomed3 = gestureStateCheckIfZoomed2;
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_FLASHED, gestureStateCheckIfZoomed3);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_TOUCHED, gestureStateWaitFingersNumberChangeWithTimeout5);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_TAPPED, gestureStateClickToFingerFirstCoords3);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_WALKED_AND_GONE, gestureStateMouseWarpToFingerLastCoords2);
        finiteStateMachine.addTransition(gestureStateCheckIfZoomed3, GestureStateCheckIfZoomed.ZOOM_OFF, gestureState1FingerMoveToScrollSync3);
        finiteStateMachine.addTransition(gestureStateCheckIfZoomed3, GestureStateCheckIfZoomed.ZOOM_ON, gestureState1FingerToZoomMove);
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout5, GestureStateWaitFingersNumberChangeWithTimeout.FINGER_TOUCHED, gestureStateWaitFingersNumberChangeWithTimeout6);
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout5, GestureStateWaitFingersNumberChangeWithTimeout.FINGER_RELEASED, gestureStatePressKey7);
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout5, GestureStateWaitFingersNumberChangeWithTimeout.TIMED_OUT, gestureState2FingersToZoom);
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout6, GestureStateWaitFingersNumberChangeWithTimeout.FINGER_RELEASED, gestureStatePressKey6);
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout6, GestureStateWaitFingersNumberChangeWithTimeout.TIMED_OUT, gestureStatePressKey6);
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout6, GestureStateWaitFingersNumberChangeWithTimeout.FINGER_TOUCHED, fSMStateRunRunnable4);
        finiteStateMachine.addTransition(gestureState2FingersToZoom, GestureState2FingersToZoom.FINGER_RELEASED, gestureState1FingerToZoomMove);
        finiteStateMachine.addTransition(gestureState1FingerToZoomMove, GestureState1FingerToZoomMove.FINGER_TOUCHED, gestureState2FingersToZoom);
        finiteStateMachine.setInitialState(gestureStateNeutral);
        finiteStateMachine.setDefaultState(gestureStateWaitForNeutral3);
        finiteStateMachine.configurationCompleted();
        GestureContext gestureContext3 = gestureContext2;
        gestureContext3.setMachine(finiteStateMachine);
        return gestureContext3;
    }
}
