package es.urjc.ia.fia.genericSearch.statistics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.PolarPoint;
import edu.uci.ics.jung.algorithms.layout.RadialTreeLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.layout.LayoutTransition;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.uci.ics.jung.visualization.util.Animator;
import es.urjc.ia.fia.genericSearch.data.Action;
import es.urjc.ia.fia.genericSearch.data.State;

public class JUNGStatistics<ACTION extends Action> extends TextStatistics<ACTION> {

	class AuxVertex {
		private State<ACTION> state = null;
		private int added = 1;
		private List<Integer> explored = null;
		private boolean duplicated;
		
		public AuxVertex(int _added, State<ACTION> _state, boolean _duplicated) {
			this.state = _state;
			this.added = _added;
			this.explored = new ArrayList<Integer>();
			this.duplicated = _duplicated;
		}
		
		
		/**
		 * @return the state
		 */
		protected State<ACTION> getState() {
			return state;
		}

		/**
		 * @return the n
		 */
		protected int getAdded() {
			return added;
		}
		

		/**
		 * @return the explored
		 */
		public List<Integer> getExplored() {
			return this.explored;
		}
		
		/**
		 * 
		 * @return
		 */
		public boolean addExplored(int _e) {
			return this.explored.add(_e);
		}
		
		/**
		 * 
		 * @return
		 */
		public boolean isExplored() {
			return !this.explored.isEmpty();
		}

		
		/**
		 * @return the duplicated
		 */
		public boolean isDuplicated() {
			return duplicated;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			try {
				AuxVertex other = (AuxVertex)obj;
				return (other.getAdded() == this.getAdded());
			} catch (Exception e) {
				return false;
			}
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return this.added;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			if (this.isExplored()) {
				if (this.getExplored().size() == 1) {
					return Integer.toString(this.getExplored().get(0));
				} else {
					return this.getExplored().toString();
				}
			} else {
				return "";
			}
		}
		
		
	}
	
	/*
	 * Visualization with JUNG
	 */
	private VisualizationViewer<AuxVertex, ACTION> vv = null;
	private Layout<AuxVertex, ACTION> treeLayout = null;
	private RadialTreeLayout<AuxVertex, ACTION> radialLayout = null;
	private Rings rings = null;
	
	private DelegateTree<AuxVertex, ACTION> tree = null;
	private Hashtable<State<ACTION>, AuxVertex> map = null;
	private int nnodesAdded = 1;
	private int nnodesExplored = 1;

	public JUNGStatistics() {
		this.tree = new DelegateTree<AuxVertex, ACTION>();
		this.map = new Hashtable<State<ACTION>, AuxVertex>();
		this.nnodesAdded = 1;
		this.nnodesExplored = 1;
		
	}

	@Override
	public void init() {
		super.init();
		this.tree = new DelegateTree<AuxVertex, ACTION>();
		this.map = new Hashtable<State<ACTION>, AuxVertex>();
		this.nnodesAdded = 1;
		this.nnodesExplored = 1;
	}
	
	
	@Override
	public void end() {
		super.end();
	}

	@Override
	public void addRootState(State<ACTION> _state) {
		super.addRootState(_state);
		if (!this.map.contains(_state)) {
			AuxVertex v = new AuxVertex(nnodesAdded++, _state, false);
			tree.addVertex(v);		
			this.map.put(_state, v);
		}
	}

	@Override
	public void addState(ACTION _action, State<ACTION> _parent, State<ACTION> _child) {
		super.addState(_action, _parent, _child);
		// Expansion without number, if now in the statistics
		if (!this.map.contains(_child)) {
			AuxVertex parent = this.map.get(_parent);
			AuxVertex child = new AuxVertex(nnodesAdded++, _child, false);
			tree.addChild(_action, parent, child);
			this.map.put(_child, child);
		}
	}
	
	@Override
	public void addDuplicateState(ACTION _action, State<ACTION> _parent, State<ACTION> _child) {
		super.addDuplicateState(_action, _parent, _child);
		
		if (!this.map.contains(_child)) {
			AuxVertex parent = this.map.get(_parent);
			// Expansion without number
			AuxVertex child = new AuxVertex(nnodesAdded++, _child, true);
			tree.addChild(_action, parent, child);
			this.map.put(_child, child);
		}
	}
	
	@Override
	public void exploreState(State<ACTION> _state) {
		super.exploreState(_state);
		this.map.get(_state).addExplored(nnodesExplored++);
	}

	@Override
	public void showStatistics() {
		
		// Layout: tree and radial
		treeLayout = new TreeLayout<AuxVertex, ACTION>(this.tree, 35, 100);
		radialLayout = new RadialTreeLayout<AuxVertex, ACTION>(this.tree, 35, 130);
		
		radialLayout.setSize(new Dimension(200 * (this.tree.getHeight()+1), 
										   200 * (this.tree.getHeight()+1)));
		
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		vv = new VisualizationViewer<AuxVertex, ACTION>(treeLayout);
		vv.setPreferredSize(new Dimension(800,600)); //Sets the viewing area size
        vv.setBackground(Color.white); // Background color
		
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<AuxVertex>());
		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<AuxVertex, ACTION>());
		
		// Setup up a new vertex to paint transformer
		Transformer<AuxVertex, Paint> vertexPaint = new Transformer<AuxVertex, Paint>() {
			@Override
			public Paint transform(AuxVertex arg0) {
				if (arg0.getState().isSolution() && arg0.isExplored()) {
					return Color.GREEN;
				} else if (arg0.isExplored()) {
					return Color.CYAN;
				} else if (arg0.isDuplicated()) {
					return Color.GRAY;
				} else {
					return Color.WHITE;
				}
			}
		};		
		
		// Tooltip for vertex
		Transformer<AuxVertex, String> toolTipsState = new Transformer<AuxVertex, String>() {

			@Override
			public String transform(AuxVertex arg0) {
				String sortInfo = "";
				if (arg0.isExplored()) {
					if (arg0.getState().isSolution() && arg0.isExplored()) {
						sortInfo = "<u><i>Solution state " + arg0.getExplored() + "</i></u>";
					} else {
						sortInfo = "<u><i>Explored state " + arg0.getExplored() + "</i></u>";						
					}
				}  else if (arg0.isDuplicated()){
					sortInfo = "<u><i>Duplicated state </i></u>";
				} else {
					sortInfo = "<u><i>Unexplored state</i></u>";					
				}
				return "<html><p>" + sortInfo + "</p>"+
						"<p>State: " + arg0.getState().toString() + "</p>" +
						"<p>Cost: " + arg0.getState().getSolutionCost() + "</p></html>";
			}
			
		};
        vv.setVertexToolTipTransformer(toolTipsState);
  
		// Tooltip for edge
		Transformer<ACTION, String> toolTipsAction = new Transformer<ACTION, String>() {

			@Override
			public String transform(ACTION arg0) {
				return "Cost: " + arg0.cost();
			}
			
		};
        vv.setEdgeToolTipTransformer(toolTipsAction);
        

		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<AuxVertex, ACTION>());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<ACTION>());
		
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		// Create a graph mouse and add it to the visualization component
		DefaultModalGraphMouse<State<ACTION>, ACTION> gm = new DefaultModalGraphMouse<State<ACTION>, ACTION>();
		gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		vv.setGraphMouse(gm);
		vv.addKeyListener(gm.getModeKeyListener());
		
		
		JFrame vFrame = new JFrame("Statistics Tree View");
		vFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		vFrame.getContentPane().add(vv);
		vFrame.pack();
		vFrame.setVisible(true);
		*/
		
        rings = new Rings();
		Container content = vFrame.getContentPane();
        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        content.add(panel);
                
        JComboBox modeBox = gm.getModeComboBox();
        modeBox.addItemListener(gm.getModeListener());
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);

        final ScalingControl scaler = new CrossoverScalingControl();

        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1.1f, vv.getCenter());
            }
        });
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1/1.1f, vv.getCenter());
            }
        });
        
        JToggleButton radial = new JToggleButton("Radial");
        radial.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					
					LayoutTransition<AuxVertex, ACTION> lt =
						new LayoutTransition<AuxVertex, ACTION>(vv, treeLayout, radialLayout);
					Animator animator = new Animator(lt);
					animator.start();
					vv.getRenderContext().getMultiLayerTransformer().setToIdentity();
					vv.addPreRenderPaintable(rings);
				} else {
					LayoutTransition<AuxVertex, ACTION> lt =
						new LayoutTransition<AuxVertex, ACTION>(vv, radialLayout, treeLayout);
					Animator animator = new Animator(lt);
					animator.start();
					vv.getRenderContext().getMultiLayerTransformer().setToIdentity();
					vv.removePreRenderPaintable(rings);
				}
				vv.repaint();
			}});

        JPanel scaleGrid = new JPanel(new GridLayout(1,0));
        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));

        /*
         * Statistics 
         */
        JLabel stats = new JLabel();
        long totalTime = endTime.getTime() - initTime.getTime();
        stats.setText(
        		"<html>" +
        		"<b>Total States:</b> " + this.totalStates + "<br>" +
        		"<b>Explored States:</b> " + this.explorerStates + "<br>" +
        		"<b>Duplicated States (detected):</b> " + this.duplicatedStates + "<br>" +
        		"<b>Solution States:</b> " + this.solutionStates + "<br>" +
        		"<b>Total time: </b>" + totalTime + " milliseconds" +
        		"</html>"
        		);
        
        JPanel legend = new JPanel();
        legend.setLayout(new BoxLayout(legend, BoxLayout.Y_AXIS));
        
        JLabel len = new JLabel("<html><b>Lengend</b></html>");
        JLabel lexpl = new JLabel("Explored state");
        lexpl.setBackground(Color.CYAN);
        lexpl.setOpaque(true);
        JLabel lune = new JLabel("Unexplored state");
        lune.setBackground(Color.WHITE);
        lune.setOpaque(true);
        JLabel ldupl = new JLabel("Duplicated state");
        ldupl.setBackground(Color.GRAY);
        ldupl.setOpaque(true);
        JLabel lsol = new JLabel("Solution state");
        lsol.setBackground(Color.GREEN);
        lsol.setOpaque(true);

        legend.add(len);
        legend.add(lexpl);
        legend.add(lune);
        legend.add(ldupl);
        legend.add(lsol);
        
        JPanel controls = new JPanel();
        controls.add(stats);
        scaleGrid.add(plus);
        scaleGrid.add(minus);
        controls.add(radial);
        controls.add(scaleGrid);
        //controls.add(modeBox);
        controls.add(legend);

        content.add(controls, BorderLayout.SOUTH);
        
		vFrame.pack();
		vFrame.setVisible(true);
	}
	
	
    class Rings implements VisualizationServer.Paintable {
    	
    	Collection<Double> depths;
    	
    	public Rings() {
    		depths = getDepths();
    	}
    	
    	private Collection<Double> getDepths() {
    		Set<Double> depths = new HashSet<Double>();
    		Map<AuxVertex,PolarPoint> polarLocations = radialLayout.getPolarLocations();
    		for(AuxVertex v : tree.getVertices()) {
    			PolarPoint pp = polarLocations.get(v);
    			depths.add(pp.getRadius());
    		}
    		return depths;
    	}

		public void paint(Graphics g) {
			g.setColor(Color.lightGray);
		
			Graphics2D g2d = (Graphics2D)g;
			Point2D center = radialLayout.getCenter();

			Ellipse2D ellipse = new Ellipse2D.Double();
			for(double d : depths) {
				ellipse.setFrameFromDiagonal(center.getX()-d, center.getY()-d, 
						center.getX()+d, center.getY()+d);
				Shape shape = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).transform(ellipse);
				g2d.draw(shape);
			}
		}

		public boolean useTransform() {
			return true;
		}
    }

	
}
