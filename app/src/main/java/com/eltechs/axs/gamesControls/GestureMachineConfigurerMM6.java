package com.eltechs.axs.gamesControls;

import com.eltechs.axs.GestureStateMachine.GestureContext;
import com.eltechs.axs.GestureStateMachine.GestureMouseMode;
import com.eltechs.axs.GestureStateMachine.GestureState1FingerMeasureSpeed;
import com.eltechs.axs.GestureStateMachine.GestureState1FingerToLongClick;
import com.eltechs.axs.GestureStateMachine.GestureState1FingerToZoomMove;
import com.eltechs.axs.GestureStateMachine.GestureState2FingersToZoom;
import com.eltechs.axs.GestureStateMachine.GestureStateCheckIfZoomed;
import com.eltechs.axs.GestureStateMachine.GestureStateCheckMouseMode;
import com.eltechs.axs.GestureStateMachine.GestureStateCheckShortZoom;
import com.eltechs.axs.GestureStateMachine.GestureStateClickToFingerFirstCoords;
import com.eltechs.axs.GestureStateMachine.GestureStateFingersMoveToKey;
import com.eltechs.axs.GestureStateMachine.GestureStateMouseWarpToFingerLastCoords;
import com.eltechs.axs.GestureStateMachine.GestureStateNeutral;
import com.eltechs.axs.GestureStateMachine.GestureStatePressKey;
import com.eltechs.axs.GestureStateMachine.GestureStateWaitFingersNumberChangeWithTimeout;
import com.eltechs.axs.GestureStateMachine.GestureStateWaitForNeutral;
import com.eltechs.axs.GestureStateMachine.PointerContext;
import com.eltechs.axs.GuestAppActionAdapters.AlignedMouseClickAdapter;
import com.eltechs.axs.GuestAppActionAdapters.MouseClickAdapterWithCheckPlacementContext;
import com.eltechs.axs.GuestAppActionAdapters.PressAndHoldMouseClickAdapter;
import com.eltechs.axs.GuestAppActionAdapters.PressAndReleaseMouseClickAdapter;
import com.eltechs.axs.GuestAppActionAdapters.SimpleMouseMoveAdapter;
import com.eltechs.axs.GuestAppActionAdapters.SimpleMousePointAndClickAdapter;
import com.eltechs.axs.KeyCodesX;
import com.eltechs.axs.PointerMoveToKeyAdapter;
import com.eltechs.axs.TouchArea;
import com.eltechs.axs.TouchEventMultiplexor;
import com.eltechs.axs.finiteStateMachine.FiniteStateMachine;
import com.eltechs.axs.finiteStateMachine.generalStates.FSMStateRunRunnable;
import com.eltechs.axs.widgets.viewOfXServer.ViewOfXServer;

public class GestureMachineConfigurerMM6 {
    private static final float clickAlignThresholdInches = 0.3f;
    private static final float doubleClickMaxDistance = 0.15f;
    private static final int doubleClickMaxIntervalMs = 200;
    private static final int fingerSpeedCheckTimeMs = 400;
    private static final float fingerStandingMaxMoveInches = 0.12f;
    private static final float fingerTapMaxMoveInches = 0.2f;
    private static final int fingerTapMaxTimeMs = 400;
    public static final float joystickDeltaInches = 0.01f;
    private static final int maxTapTimeMs = 100;
    private static final int mouseActionSleepMs = 50;

    public static GestureContext createGestureContext(ViewOfXServer viewOfXServer, TouchArea touchArea, TouchEventMultiplexor touchEventMultiplexor, int i, GestureMouseMode gestureMouseMode, Runnable runnable) {
        // MouseClickAdapterWithCheckPlacementContext mouseClickAdapterWithCheckPlacementContext;
        ViewOfXServer viewOfXServer2 = viewOfXServer;
        GestureContext gestureContext = new GestureContext(viewOfXServer2, touchArea, touchEventMultiplexor);
        PointerContext pointerContext = new PointerContext();
        GestureStateNeutral gestureStateNeutral = new GestureStateNeutral(gestureContext);
        GestureStateWaitForNeutral gestureStateWaitForNeutral = new GestureStateWaitForNeutral(gestureContext);
        float f = (float) i;
        float f2 = fingerStandingMaxMoveInches * f;
        GestureState1FingerMeasureSpeed gestureState1FingerMeasureSpeed = new GestureState1FingerMeasureSpeed(gestureContext, 400, f2, f2, fingerTapMaxMoveInches * f, 400.0f);
        GestureStateCheckIfZoomed gestureStateCheckIfZoomed = new GestureStateCheckIfZoomed(gestureContext);
        GestureStateCheckShortZoom gestureStateCheckShortZoom = new GestureStateCheckShortZoom(gestureContext);
        GestureStateMouseWarpToFingerLastCoords gestureStateMouseWarpToFingerLastCoords = new GestureStateMouseWarpToFingerLastCoords(gestureContext, new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), pointerContext);
        GestureStateWaitForNeutral gestureStateWaitForNeutral2 = gestureStateWaitForNeutral;
        GestureStateCheckShortZoom gestureStateCheckShortZoom2 = gestureStateCheckShortZoom;
        SimpleMousePointAndClickAdapter simpleMousePointAndClickAdapter = new SimpleMousePointAndClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50), pointerContext);
        float f3 = clickAlignThresholdInches * f;
        ViewOfXServer viewOfXServer3 = viewOfXServer2;
        PointerContext pointerContext2 = pointerContext;
        GestureStateMouseWarpToFingerLastCoords gestureStateMouseWarpToFingerLastCoords2 = gestureStateMouseWarpToFingerLastCoords;
        AlignedMouseClickAdapter alignedMouseClickAdapter = new AlignedMouseClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50), viewOfXServer3, pointerContext2, f3);
        AlignedMouseClickAdapter alignedMouseClickAdapter2 = new AlignedMouseClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50), new PressAndReleaseMouseClickAdapter(gestureContext.getPointerReporter(), 1, 50), viewOfXServer3, pointerContext2, 0.15f * f);
        MouseClickAdapterWithCheckPlacementContext mouseClickAdapterWithCheckPlacementContext2 = new MouseClickAdapterWithCheckPlacementContext(simpleMousePointAndClickAdapter, alignedMouseClickAdapter, alignedMouseClickAdapter2, pointerContext, 200);
        GestureStateClickToFingerFirstCoords gestureStateClickToFingerFirstCoords = new GestureStateClickToFingerFirstCoords(gestureContext, mouseClickAdapterWithCheckPlacementContext2);
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout = new GestureStateWaitFingersNumberChangeWithTimeout(gestureContext, 100);
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout2 = new GestureStateWaitFingersNumberChangeWithTimeout(gestureContext, 100);
        FSMStateRunRunnable fSMStateRunRunnable = new FSMStateRunRunnable(runnable);
        GestureStatePressKey gestureStatePressKey = new GestureStatePressKey(gestureContext, KeyCodesX.KEY_SPACE);
        float f4 = f * 0.01f;
        GestureStatePressKey gestureStatePressKey2 = new GestureStatePressKey(gestureContext, KeyCodesX.KEY_RETURN);
        GestureStatePressKey gestureStatePressKey3 = gestureStatePressKey;
        FSMStateRunRunnable fSMStateRunRunnable2 = fSMStateRunRunnable;
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout3 = gestureStateWaitFingersNumberChangeWithTimeout2;
        float f5 = f4;
        PointerMoveToKeyAdapter pointerMoveToKeyAdapter = new PointerMoveToKeyAdapter(f5, 1000000.0f, new KeyCodesX[]{KeyCodesX.KEY_UP}, new KeyCodesX[]{KeyCodesX.KEY_DOWN}, new KeyCodesX[]{KeyCodesX.KEY_CONTROL_LEFT, KeyCodesX.KEY_LEFT, KeyCodesX.KEY_LEFT}, new KeyCodesX[]{KeyCodesX.KEY_CONTROL_LEFT, KeyCodesX.KEY_RIGHT, KeyCodesX.KEY_RIGHT}, KeyCodesX.KEY_NONE, true, gestureContext.getKeyboardReporter());
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout4 = gestureStateWaitFingersNumberChangeWithTimeout;
        PointerMoveToKeyAdapter pointerMoveToKeyAdapter2 = new PointerMoveToKeyAdapter(f5, 1000000.0f, new KeyCodesX[]{KeyCodesX.KEY_NONE}, new KeyCodesX[]{KeyCodesX.KEY_NONE}, new KeyCodesX[]{KeyCodesX.KEY_LEFT}, new KeyCodesX[]{KeyCodesX.KEY_RIGHT}, KeyCodesX.KEY_NONE, true, gestureContext.getKeyboardReporter());
        GestureStateFingersMoveToKey gestureStateFingersMoveToKey = new GestureStateFingersMoveToKey(gestureContext, pointerMoveToKeyAdapter, pointerMoveToKeyAdapter2);
        GestureState1FingerToZoomMove gestureState1FingerToZoomMove = new GestureState1FingerToZoomMove(gestureContext);
        GestureState2FingersToZoom gestureState2FingersToZoom = new GestureState2FingersToZoom(gestureContext);
        GestureStateFingersMoveToKey gestureStateFingersMoveToKey2 = gestureStateFingersMoveToKey;
        SimpleMousePointAndClickAdapter simpleMousePointAndClickAdapter2 = new SimpleMousePointAndClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndHoldMouseClickAdapter(gestureContext.getPointerReporter(), 3), pointerContext);
        GestureState2FingersToZoom gestureState2FingersToZoom2 = gestureState2FingersToZoom;
        GestureStateFingersMoveToKey gestureStateFingersMoveToKey3 = gestureStateFingersMoveToKey2;
        GestureState1FingerToZoomMove gestureState1FingerToZoomMove2 = gestureState1FingerToZoomMove;
        GestureStatePressKey gestureStatePressKey4 = gestureStatePressKey2;
        GestureStatePressKey gestureStatePressKey5 = gestureStatePressKey3;
        SimpleMousePointAndClickAdapter simpleMousePointAndClickAdapter3 = simpleMousePointAndClickAdapter2;
        FSMStateRunRunnable fSMStateRunRunnable3 = fSMStateRunRunnable2;
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout5 = gestureStateWaitFingersNumberChangeWithTimeout3;
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout6 = gestureStateWaitFingersNumberChangeWithTimeout4;
        AlignedMouseClickAdapter alignedMouseClickAdapter3 = new AlignedMouseClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndHoldMouseClickAdapter(gestureContext.getPointerReporter(), 3), new PressAndHoldMouseClickAdapter(gestureContext.getPointerReporter(), 3), viewOfXServer2, pointerContext, f3);
        MouseClickAdapterWithCheckPlacementContext mouseClickAdapterWithCheckPlacementContext3 = new MouseClickAdapterWithCheckPlacementContext(simpleMousePointAndClickAdapter3, alignedMouseClickAdapter3, new SimpleMousePointAndClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndHoldMouseClickAdapter(gestureContext.getPointerReporter(), 3), pointerContext), pointerContext, 200);
        GestureState1FingerToLongClick gestureState1FingerToLongClick = new GestureState1FingerToLongClick(gestureContext, mouseClickAdapterWithCheckPlacementContext3);
        SimpleMousePointAndClickAdapter simpleMousePointAndClickAdapter4 = new SimpleMousePointAndClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndHoldMouseClickAdapter(gestureContext.getPointerReporter(), 1), pointerContext);
        GestureState1FingerToLongClick gestureState1FingerToLongClick2 = gestureState1FingerToLongClick;
        AlignedMouseClickAdapter alignedMouseClickAdapter4 = new AlignedMouseClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndHoldMouseClickAdapter(gestureContext.getPointerReporter(), 1), new PressAndHoldMouseClickAdapter(gestureContext.getPointerReporter(), 1), viewOfXServer2, pointerContext, f3);
        MouseClickAdapterWithCheckPlacementContext mouseClickAdapterWithCheckPlacementContext = new MouseClickAdapterWithCheckPlacementContext(simpleMousePointAndClickAdapter4, alignedMouseClickAdapter4, new SimpleMousePointAndClickAdapter(new SimpleMouseMoveAdapter(gestureContext.getPointerReporter()), new PressAndHoldMouseClickAdapter(gestureContext.getPointerReporter(), 1), pointerContext), pointerContext, 200);
        GestureState1FingerToLongClick gestureState1FingerToLongClick3 = new GestureState1FingerToLongClick(gestureContext, mouseClickAdapterWithCheckPlacementContext);
        FiniteStateMachine finiteStateMachine = new FiniteStateMachine();
        GestureStateCheckMouseMode gestureStateCheckMouseMode = new GestureStateCheckMouseMode(gestureContext, gestureMouseMode);
        GestureStateMouseWarpToFingerLastCoords gestureStateMouseWarpToFingerLastCoords3 = gestureStateMouseWarpToFingerLastCoords2;
        finiteStateMachine.setStatesList(gestureStateNeutral, gestureState1FingerMeasureSpeed, gestureStateMouseWarpToFingerLastCoords3, gestureStateClickToFingerFirstCoords, gestureStateFingersMoveToKey3, gestureStateCheckIfZoomed, gestureStateCheckShortZoom2, gestureStateWaitFingersNumberChangeWithTimeout6, gestureStateWaitFingersNumberChangeWithTimeout5, fSMStateRunRunnable3, gestureStatePressKey5, gestureStatePressKey4, gestureState1FingerToZoomMove2, gestureState2FingersToZoom2, gestureStateCheckMouseMode, gestureState1FingerToLongClick3, gestureState1FingerToLongClick2, gestureStateWaitForNeutral2);
        GestureStateWaitForNeutral gestureStateWaitForNeutral3 = gestureStateWaitForNeutral2;
        finiteStateMachine.addTransition(gestureStateWaitForNeutral3, GestureStateWaitForNeutral.GESTURE_COMPLETED, gestureStateNeutral);
        finiteStateMachine.addTransition(gestureStateNeutral, GestureStateNeutral.FINGER_TOUCHED, gestureState1FingerMeasureSpeed);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_STANDING, gestureStateCheckMouseMode);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_WALKED, gestureStateFingersMoveToKey3);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_FLASHED, gestureStateCheckIfZoomed);
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout7 = gestureStateWaitFingersNumberChangeWithTimeout6;
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_TOUCHED, gestureStateWaitFingersNumberChangeWithTimeout7);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_TAPPED, gestureStateClickToFingerFirstCoords);
        finiteStateMachine.addTransition(gestureStateCheckMouseMode, GestureStateCheckMouseMode.MOUSE_MODE_LEFT, gestureState1FingerToLongClick3);
        finiteStateMachine.addTransition(gestureStateCheckMouseMode, GestureStateCheckMouseMode.MOUSE_MODE_RIGHT, gestureState1FingerToLongClick2);
        finiteStateMachine.addTransition(gestureState1FingerMeasureSpeed, GestureState1FingerMeasureSpeed.FINGER_WALKED_AND_GONE, gestureStateMouseWarpToFingerLastCoords3);
        finiteStateMachine.addTransition(gestureStateCheckIfZoomed, GestureStateCheckIfZoomed.ZOOM_OFF, gestureStateFingersMoveToKey3);
        GestureState1FingerToZoomMove gestureState1FingerToZoomMove3 = gestureState1FingerToZoomMove2;
        finiteStateMachine.addTransition(gestureStateCheckIfZoomed, GestureStateCheckIfZoomed.ZOOM_ON, gestureState1FingerToZoomMove3);
        GestureStateWaitFingersNumberChangeWithTimeout gestureStateWaitFingersNumberChangeWithTimeout8 = gestureStateWaitFingersNumberChangeWithTimeout5;
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout7, GestureStateWaitFingersNumberChangeWithTimeout.FINGER_TOUCHED, gestureStateWaitFingersNumberChangeWithTimeout8);
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout7, GestureStateWaitFingersNumberChangeWithTimeout.FINGER_RELEASED, gestureStatePressKey4);
        GestureStateCheckShortZoom gestureStateCheckShortZoom3 = gestureStateCheckShortZoom2;
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout7, GestureStateWaitFingersNumberChangeWithTimeout.TIMED_OUT, gestureStateCheckShortZoom3);
        GestureState2FingersToZoom gestureState2FingersToZoom3 = gestureState2FingersToZoom2;
        finiteStateMachine.addTransition(gestureStateCheckShortZoom3, GestureStateCheckShortZoom.ZOOM_SHORT, gestureState2FingersToZoom3);
        GestureStatePressKey gestureStatePressKey6 = gestureStatePressKey5;
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout8, GestureStateWaitFingersNumberChangeWithTimeout.FINGER_RELEASED, gestureStatePressKey6);
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout8, GestureStateWaitFingersNumberChangeWithTimeout.TIMED_OUT, gestureStatePressKey6);
        finiteStateMachine.addTransition(gestureStateWaitFingersNumberChangeWithTimeout8, GestureStateWaitFingersNumberChangeWithTimeout.FINGER_TOUCHED, fSMStateRunRunnable3);
        finiteStateMachine.addTransition(gestureState2FingersToZoom3, GestureState2FingersToZoom.FINGER_RELEASED, gestureState1FingerToZoomMove3);
        finiteStateMachine.addTransition(gestureState1FingerToZoomMove3, GestureState1FingerToZoomMove.FINGER_TOUCHED, gestureState2FingersToZoom3);
        finiteStateMachine.setInitialState(gestureStateNeutral);
        finiteStateMachine.setDefaultState(gestureStateWaitForNeutral3);
        finiteStateMachine.configurationCompleted();
        gestureContext.setMachine(finiteStateMachine);
        return gestureContext;
    }
}
