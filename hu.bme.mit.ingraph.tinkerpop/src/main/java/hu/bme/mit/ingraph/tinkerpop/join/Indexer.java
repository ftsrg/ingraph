//package hu.bme.mit.ingraph.tinkerpop.join;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Collection;
//import java.util.LinkedList;
//
//import org.apache.tinkerpop.shaded.kryo.Kryo;
//import org.apache.tinkerpop.shaded.kryo.io.Input;
//import org.apache.tinkerpop.shaded.kryo.io.Output;
//import org.omg.CORBA.portable.ValueFactory;
//
//public class Indexer {
//
//	protected static final String INDEXER_DIR = "/home/szarnyasg/index/";
//	protected static final String SWITCH = "switch";
//	protected static final String DEFINEDBY = "definedBy";
//	protected static final String SENSOR_EDGE = "sensor";
//	protected static final String FOLLOWS = "follows";
//	protected static final String MODEL_PREFIX = "/home/szarnyasg/git/trainbenchmark/models/railway-repair-";
//	protected static final String MODEL_EXTENSION = ".ttl";
//
//	protected static final String BASE_PREFIX = "http://www.semanticweb.org/ontologies/2015/trainbenchmark#";
//	protected static final String FOLLOWS_TYPE = BASE_PREFIX + FOLLOWS;
//	protected static final String SENSOR_EDGE_TYPE = BASE_PREFIX + SENSOR_EDGE;
//	protected static final String DEFINEDBY_TYPE = BASE_PREFIX + DEFINEDBY;
//	protected static final String SWITCH_TYPE = BASE_PREFIX + SWITCH;
//	protected static final String INDEXER_EXTENSION = ".bin";
//
//	protected Collection<Tuple> switches = new LinkedList<>();
//	protected Collection<Tuple> followss = new LinkedList<>();
//	protected Collection<Tuple> sensors = new LinkedList<>();
//	protected Collection<Tuple> definedBys = new LinkedList<>();
//
//	protected final MyCollector collector = new MyCollector();
//	protected final ValueFactory f = new ValueFactoryImpl();
//
//	public void load(final int size) throws IOException {
//		final File modelFile = new File(MODEL_PREFIX + size + MODEL_EXTENSION);
//
//		final TurtleParser parser = new TurtleParser();
//		final InputStream inputStream = new FileInputStream(modelFile);
//
//		collector.registerCollection(f, SWITCH_TYPE, switches);
//		collector.registerCollection(f, FOLLOWS_TYPE, followss);
//		collector.registerCollection(f, SENSOR_EDGE_TYPE, sensors);
//		collector.registerCollection(f, DEFINEDBY_TYPE, definedBys);
//
//		try {
//			parser.setRDFHandler(collector);
//			parser.setValueFactory(f);
//			parser.parse(inputStream, "");
//		} catch (RDFParseException | IOException | RDFHandlerException e) {
//			throw new IOException(e);
//		}
//
//		final String sizePrefix = size + "-";
//		serialize(sizePrefix + SWITCH, switches);
//		serialize(sizePrefix + FOLLOWS, followss);
//		serialize(sizePrefix + SENSOR_EDGE, sensors);
//		serialize(sizePrefix + DEFINEDBY, definedBys);
//	}
//
//	public Collection<Tuple> getSwitches() {
//		return switches;
//	}
//
//	public Collection<Tuple> getFollowss() {
//		return followss;
//	}
//
//	public Collection<Tuple> getSensors() {
//		return sensors;
//	}
//
//	public Collection<Tuple> getDefinedBys() {
//		return definedBys;
//	}
//
//	public void serialize(final String fileName, final Object collection) throws IOException {
//		final String filePath = INDEXER_DIR + fileName + INDEXER_EXTENSION;
//		// final FileOutputStream fileOut = new FileOutputStream(filePath);
//		// final ObjectOutputStream out = new ObjectOutputStream(fileOut);
//		// out.writeObject(collection);
//		// out.close();
//		// fileOut.close();
//
//		final Kryo kryo = new Kryo();
//
//		ImmutableListSerializer.registerSerializers(kryo);
//		final Output output = new Output(new FileOutputStream(filePath));
//		kryo.writeObject(output, collection);
//		output.close();
//	}
//
//	public Collection<Tuple> deserialize(final String fileName) throws IOException, ClassNotFoundException {
//		final String filePath = INDEXER_DIR + fileName + INDEXER_EXTENSION;
//		// final FileInputStream fileIn = new FileInputStream(filePath);
//		// final ObjectInputStream in = new ObjectInputStream(fileIn);
//		// final Object collection = in.readObject();
//		// in.close();
//		// fileIn.close();
//
//		final Kryo kryo = new Kryo();
//		ImmutableListSerializer.registerSerializers(kryo);
//		final Input input = new Input(new FileInputStream(filePath));
//
//		final Collection collection = kryo.readObject(input, LinkedList.class);
//		return collection;
//	}
//
//	public void reload(final int size) throws IOException, ClassNotFoundException {
//		final String sizePrefix = size + "-";
//		switches = deserialize(sizePrefix + SWITCH);
//		followss = deserialize(sizePrefix + FOLLOWS);
//		sensors = deserialize(sizePrefix + SENSOR_EDGE);
//		definedBys = deserialize(sizePrefix + DEFINEDBY);
//	}
//
//}
