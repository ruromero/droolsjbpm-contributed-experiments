package org.drools.eclipse.osworkflow.editor.editpart;
/*
 * Copyright 2005 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.drools.eclipse.flow.common.editor.editpart.ElementEditPart;
import org.drools.eclipse.flow.common.editor.editpart.figure.AbstractElementFigure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * EditPart for an action node.
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class StepNodeEditPart extends ElementEditPart {

    private static final Color color = new Color(Display.getCurrent(), 255, 250, 205);
    
    protected IFigure createFigure() {
        return new StepNodeFigure();
    }
    
    public static class StepNodeFigure extends AbstractElementFigure {
        
        private RoundedRectangle rectangle;
        
        protected void customizeFigure() {
            rectangle = new RoundedRectangle();
            rectangle.setCornerDimensions(new Dimension(25, 25));
            add(rectangle, 0);
            rectangle.setBackgroundColor(color);
            rectangle.setBounds(getBounds());
            setSelected(false);
        }
        
        public void setBounds(Rectangle rectangle) {
            super.setBounds(rectangle);
            this.rectangle.setBounds(rectangle);
        }
        
        public void setSelected(boolean b) {
            super.setSelected(b);
            rectangle.setLineWidth(b ? 3 : 1);
            repaint();
        }
    }
}
