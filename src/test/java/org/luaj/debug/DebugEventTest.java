package org.luaj.debug;

import java.io.IOException;

import junit.framework.TestCase;

import org.luaj.debug.event.DebugEventBreakpoint;

public class DebugEventTest extends TestCase {
    public void testDebugEventSerialization() {
        try {
            DebugMessage event = new DebugMessage(DebugMessageType.started);
            byte[] data = SerializationHelper.serialize(event);
            DebugMessage eventOut = (DebugMessage) SerializationHelper
                    .deserialize(data);
            assertNotNull(eventOut);
            assertEquals(event.getType(), eventOut.getType());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    public void testDebugEventBreakpointSerialization() {
        try {
            DebugEventBreakpoint event = new DebugEventBreakpoint("test.lua",
                    100);
            byte[] data = SerializationHelper.serialize(event);
            DebugEventBreakpoint eventOut = (DebugEventBreakpoint) SerializationHelper
                    .deserialize(data);
            assertNotNull(eventOut);
            assertEquals(event.getSource(), eventOut.getSource());
            assertEquals(event.getLineNumber(), eventOut.getLineNumber());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}