package com.kv.j8.streams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;


import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStreams {
	
	public static List<String> list = new ArrayList<String>();
	
	public static boolean sundayDeliveryAvailable(String area, String postcode){
		return true;
	}
	public static void main(String... args){
		
		List<Integer> li = new ArrayList<Integer>();
		li.add(1);
		li.add(2);
		li.add(3);
		li.add(4);
		li.add(5);
		li.add(6);
		
		Map<Integer, List<Integer>> listEvenOrOd = li.stream()
												.collect(Collectors.groupingBy(p -> p%2));
		
		listEvenOrOd.forEach((h,val) -> System.out.format("Age %s: %s\n",h,val));

		
		
		List<String> sundayOpenAreas = new ArrayList<String>();
		for (int i = 1; i <= sundayOpenAreas.size(); i++) {
		    String area = sundayOpenAreas.get(i - 1);
		    String postcode = "XX" + i + " 1AA";

		    boolean sundayDeliveryAvailable = sundayDeliveryAvailable(area, postcode);
		    //Assert.assertTrue(sundayDeliveryAvailable, area + " should accept deliveries on Sunday to " + postcode + "!");
		    System.out.println(area + ", " + postcode);
		}
		IntStream.of(sundayOpenAreas.size())
						.filter(i -> {
							String postcode = "XX" + i + " 1AA";
					        System.out.println("filter: " + i);
					        return sundayDeliveryAvailable(String.valueOf(i), postcode);
					    })
						.forEach(System.out::println);
		
		
		for(int i=0;i<10;i++){
			list.add("String "+i);
		}
		
		
		
		//list.stream().collect(collector)
		
		Stream.of("a1","a2","a3")
				.map(i -> i + "ds")
				.findFirst()
				.ifPresent(System.out::println);
		
		Arrays.asList("my", "name", "karan")
				.stream()
				.map(i -> "Item Length is : "+i.length())
				.forEach(System.out::println);
		
		
		Arrays.stream(new int[]{1, 2, 3})
			.map(i -> 2 *i +1)
			.average()
			.ifPresent(System.out::println);
		
		
		//Convert map to primitive
		Stream.of("a1", "a2", "a3")
			.map(i -> i.substring(1))
			.mapToInt(Integer::parseInt)
			.max()
			.ifPresent(System.out::println);
		
		
		//Simple Pipeline operations
		Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8 };
		List<Integer> listOfIntegers =
		    new ArrayList<>(Arrays.asList(intArray));

		System.out.println("listOfIntegers:");
		listOfIntegers
		    .stream()
		    .forEach(e -> System.out.print(e + " "));
		System.out.println("");

		System.out.println("listOfIntegers sorted in reverse order:");
		Comparator<Integer> normal = Integer::compare;
		Comparator<Integer> reversed = normal.reversed(); 
		Collections.sort(listOfIntegers, reversed);  
		listOfIntegers
		    .stream()
		    .forEach(e -> System.out.print(e + " "));
		System.out.println("");
		     
		System.out.println("Parallel stream");
		listOfIntegers
		    .parallelStream()
		    .forEach(e -> System.out.print(e + " "));
		System.out.println("");
		    
		System.out.println("Another parallel stream:");
		listOfIntegers
		    .parallelStream()
		    .forEach(e -> System.out.print(e + " "));
		System.out.println("");
		     
		System.out.println("With forEachOrdered:");
		listOfIntegers
		    .parallelStream()
		    .forEachOrdered(e -> System.out.print(e + " "));
		System.out.println("");
		
		
		//matching 
		
		
		Stream.of("d2", "a2", "b1", "b3", "c")
		    .map(s -> {
		        System.out.println("map: " + s);
		        return s.toUpperCase();
		    })
		    .anyMatch(s -> {
		        System.out.println("anyMatch: " + s);
		        return s.startsWith("A");
		    });
		
		Stream.of("d2", "a2", "b1", "b3", "c")
		    .map(s -> s.toUpperCase())
		    .anyMatch(s -> s.startsWith("A"));
		    
			
		Stream.of("d2", "a2", "b1", "b3", "c")
		    .filter(s -> {
		    	s.startsWith("b");
		        System.out.println("filter: " + s);
		        return true;
		    });
		
		//ordering of intermediate functions  
		// map and filter
		Stream.of("d2", "a2", "b1", "b3", "c")
		    .map(s -> {
		        System.out.println("map: " + s);
		        return s.toUpperCase();
		    })
		    .filter(s -> {
		        System.out.println("filter: " + s);
		        return s.startsWith("A");
		    })
		    .forEach(s -> System.out.println("forEach: " + s));
		
		// filter and then map
		Stream.of("d2", "a2", "b1", "b3", "c")
		    .filter(s -> {
		        System.out.println("filter: " + s);
		        return s.startsWith("a");
		    })
		    .map(s -> {
		        System.out.println("map: " + s);
		        return s.toUpperCase();
		    })
		    .forEach(s -> System.out.println("forEach: " + s));
		
		//sort, filter and map
		Stream.of("d2", "a2", "b1", "b3", "c")
		    .sorted((s1, s2) -> {
		        System.out.printf("sort: %s; %s\n", s1, s2);
		        return s1.compareTo(s2);
		    })
		    .filter(s -> {
		        System.out.println("filter: " + s);
		        return s.startsWith("a");
		    })
		    .map(s -> {
		        System.out.println("map: " + s);
		        return s.toUpperCase();
		    })
		    .forEach(s -> System.out.println("forEach: " + s));
		
		// filter, sort and map
		System.out.println(" --- Filter Sort and Map ---");
		Stream.of("d2", "a2", "b1", "b3", "c")
			.filter(f -> {
				System.out.println("filer "+f);
				return f.startsWith("b");
			})
			.sorted((s1, s2) -> {
				System.out.println("Sort "+s1+" "+s2);
				return s1.compareTo(s2);
			})
			.map(m -> {
				System.out.println("map "+m);
				return m.toUpperCase();
			})
			.forEach(s -> System.out.println("forEach "+s));
		
		
		/* **************** Collect Interface ***************/
		

		List<Person> persons =
		    Arrays.asList(
		        new Person("Max", 18),
		        new Person("Peter", 23),
		        new Person("Pamela", 23),
		        new Person("David", 12),
		        new Person("David", 12),
		        new Person("Pam", 12));
		
		boolean matchedResult = persons.stream()
                .allMatch((s) -> s.name.startsWith("P"));
									
		
		System.out.println("******* Collect *******");
		System.out.println();
		
		List<Person> filtered = persons.stream()
			        	.filter(p -> p.name.startsWith("P"))
			        	.collect(Collectors.toList());
		
		System.out.println("Collected List "+filtered);    // [Peter, Pamela]
		
		Set<Person> personSet = persons.stream()
								.filter(p -> {
									//System.out.println("Filter Called : "+p.name);
									return p.age > 20;
								})
								.collect(Collectors.toCollection(HashSet::new));
		System.out.println("Collected Set : "+personSet);
		
		
		// Group By Age
		Map<Integer, List<Person>> personByAge = persons.stream()
														.collect(Collectors.groupingBy(p -> p.age));
		personByAge.forEach((age,p) -> System.out.format("Age %s: %s\n",age,p) );
		
		//group by composite key
		Map<String, List<Person>> personByAgeName = persons.stream()
				.collect(Collectors.groupingBy(p -> (p.name+p.age)));
		personByAgeName.forEach((age,p) -> System.out.format("Age+Name %s: %s\n",age,p) );
		
		
		//Average Age
		Double averageAge = persons.stream()
									.collect(Collectors.averagingInt(p -> p.age));
		System.out.println("Average age : "+averageAge);
		
		//Summarizing Collector
		
		IntSummaryStatistics intSum = persons.stream()
											.collect(Collectors.summarizingInt(p -> p.age));
		System.out.println("Summarizing Person Age: "+intSum);
		
		// Joining Phrases
		String phrase = persons.stream()
								.filter(p -> p.age>=18)
								.map(p -> p.name)
								.collect(Collectors.joining(" and ", "In India ", " are of legal age"));
		System.out.println("Phrase : "+phrase);
		
		//Transform to Map
		System.out.println();
		System.out.println("******* Transforming to Map ******");
		System.out.println();
		
		Map<Integer, String> mb = persons.stream()
										.collect(Collectors.toMap(
												p -> p.age, 
												p -> p.name, 
												(name1, name2) -> name1+";"+name2,
												ConcurrentHashMap::new));
		ConcurrentMap<Integer, String> mb1 = persons.stream()
				                         .collect(Collectors.toConcurrentMap(
				                        		 p -> p.age, 
				                        		 p -> p.name,
				                        		 (name1, name2) -> name1+";"+name2));
		
		Map<Boolean, List<Person>> partitioned = persons.stream().
                collect(Collectors.partitioningBy(p -> p.age > 20));

		
		Map<Boolean, Map<Object, List<Person>>> rr = persons.stream()
												.collect(Collectors.partitioningBy(p -> p.name.startsWith("P"), 
														Collectors.groupingBy(p -> p.age > 20)));
		
		System.out.println("rr = "+rr);
		
		
		
		Map<Object, List<Person>> grouped = persons.stream()
                .collect(Collectors.groupingBy(p -> p));
		
		
		System.out.println("Collect to Map : "+mb);
		System.out.println("Collect to ConcurrentMap : "+mb1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println("TIme 1 = "+sdf.format(System.currentTimeMillis()));
		Map<Integer, List<Person>> myMap = persons.stream()
												.collect(Collectors.groupingBy(p -> p.age));
		System.out.println("TIme 2 = "+sdf.format(System.currentTimeMillis()));
		System.out.println("MyMap = "+myMap);
		
		System.out.println("TIme 3 = "+sdf.format(System.currentTimeMillis()));
		ConcurrentMap<Integer, List<Person>> myConMap = persons.parallelStream()
															.collect(Collectors.groupingByConcurrent(p -> p.age));
		System.out.println("TIme 4 = "+sdf.format(System.currentTimeMillis()));
		System.out.println("MyConMap = "+myConMap);
		
		//Custom Collector
		System.out.println();
		System.out.println("******* Custom Collector ******");
		System.out.println();
		
		Collector<Person, StringJoiner, String> personNameCollector = 
				Collector.of(
						() -> new StringJoiner("|"),		//supplier
						(j,p) -> j.add(p.name.toUpperCase()),	//accumulator
						(j1, j2) -> j1.merge(j2),			//combiner
						StringJoiner::toString,
						Characteristics.CONCURRENT);			//finisher
		
		String names = persons.stream()
			    .collect(personNameCollector);
		
		String names2 = persons.stream()
			    .collect(new MyCustomCollector());
		System.out.println("Names : "+names);
		
		System.out.println("Names With Implementing Collector : "+names2);
		
		
		//Custom Collector
		System.out.println();
		System.out.println("******* Flat Mapr ******");
		System.out.println();
		
		List<Foo> foos = new ArrayList<Foo>();
		
		IntStream
	    	.range(1, 4)
	    	.forEach(i -> foos.add(new Foo("Foo" + i)));
		
		foos.forEach(f ->
				IntStream.range(1,4)
						.forEach(i -> f.bars.add(new Bar("Bar"+i+" -> "+f.name))));
		
		foos.stream()
			.flatMap(f -> f.bars.stream())
			.forEach(i -> System.out.println("Bar Name : "+i.name));
		
		//Solution from SO
//		IntStream.range(1, 4)
//        .mapToObj (i -> {Foo foo = new Foo("Foo" + i);
//                         foo.bars =
//                             IntStream.range(1,4)
//                                      .mapToObj(i -> new Bar("Bar"+i+" -> "+f.name))
//                                      .collect(Collectors.toList());
//                   })
//        .flatMap (f -> f.bars.stream())
//        .forEach(i -> System.out.println("Bar Name : "+i.name));
		
		// SO answer
		IntStream.range(1, 4)
        .mapToObj(i -> "Foo" + i)
        .flatMap(name -> IntStream.range(1, 4)
           .mapToObj(i -> "Bar" + i + "->" + name))
        .forEach(System.out::println);
		
		// FlatMap with single pipeline of execution
		IntStream.range(1, 4)
				.mapToObj(i -> new Foo("Foo"+i))
				.peek(f -> IntStream.range(1, 4)
						.mapToObj(i -> new Bar("Bar"+i+"->"+f.name))
						.forEach(f.bars::add))
				.flatMap(f -> f.bars.stream())
				.forEach(b -> System.out.println("Bar : "+b.name));
		
		
		// Reduce
		System.out.println();
		System.out.println("******* Reduce ******");
		System.out.println();
		
		persons
		    .stream()
		    .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
		    .ifPresent(System.out::println);    // Pamela
		
		persons.stream()
				.reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
				.ifPresent(System.out::println);
		
		// Reduce function Binary Function and Accumulator
		
		Person per = persons.stream()
				      	.reduce(new Person("",0),
				      			(p1, p2) -> {
				      				p1.age += p2.age;
				      				p1.name += p2.name +" : ";
				      				return p1;
				      			});
		System.out.println("\n New Accumulated Person > "+per);
		
		//Reduce with an identity value, a BiFunction accumulator 
		//and a combiner function of type BinaryOperator
		
		Integer ageSum = persons.stream()
							.reduce(0, 
									(sum, p) -> sum += p.age, 
									(sum1, sum2) -> sum1+sum2);
		System.out.println("\n Age Sum "+ageSum);
		
		//Combiner not getting executed
		Integer ageSumComb = persons
			    .stream()
			    .reduce(0,
			        (sum, p) -> {
			            System.out.println("Accumulator: Sum= "+ sum + " Person= " + p);
			            return sum += p.age;
			        },
			        (sum1, sum2) -> {
			            System.out.format("Combiner: Sum1= " + sum1 + " Sum2= "+ sum2);
			            return sum1 + sum2;
			        });
		System.out.println("ageSumComb = "+ageSumComb);
		
		//Calling reduce with parallel stream
		System.out.println("Checking Parallel Stream Reducer-----");
		Integer parallelSum = persons.parallelStream()
									.reduce(0,
											(sum, p) -> {
												System.out.println("Accumulator : Sum = "+sum+" Person : "+p);
												sum += p.age;
												return sum;
											}, 
											(sum1, sum2) -> {
												System.out.println("Combiner : Sum1 = "+sum1+" Sum2 = "+sum2);
												return sum1+sum2;
											});
		
		System.out.println("\n Parallel Stream Reducer : "+parallelSum);
		
		
		List<Person> breakOp = persons.stream()
	        	.filter(p -> p.name.startsWith("P"))
	        	.collect(Collectors.toList());
		

		System.out.println("Collected List "+breakOp); 
		
		System.out.println();
		System.out.println("************** Statistics Example *******************");
		System.out.println();
		//Statistics example
		List<OrderEntry> orderEntries = new ArrayList<>();
	    orderEntries.add(new OrderEntry(10));
	    orderEntries.add(new OrderEntry(14));
	    orderEntries.add(new OrderEntry(12));
	    orderEntries.add(new OrderEntry(18));

	    System.out.println(" Stats 1 TIme 1 = "+sdf.format(System.currentTimeMillis()));
		IntSummaryStatistics stats1 = orderEntries.stream()
	            .mapToInt((x) -> x.getAmount()).summaryStatistics();
		System.out.println("stats 1 TIme 2 = "+sdf.format(System.currentTimeMillis()));
		
		System.out.println("Stats 1 = "+stats1);

		System.out.println(" Stats 2 TIme 1 = "+sdf.format(System.currentTimeMillis()));
		IntSummaryStatistics stats2 = orderEntries.stream().collect(
	            Collectors.summarizingInt(o -> o.getAmount()));
		System.out.println(" Stats 2 TIme 1 = "+sdf.format(System.currentTimeMillis()));
		
		
		
		IntSummaryStatistics stats3 = orderEntries.stream().collect(
	            Collectors.summarizingInt(OrderEntry :: getAmount));
		System.out.println("Stats 2 = "+stats2);

		
		IntSummaryStatistics istats = IntStream.of(51,22,50,27,35).
	            collect(IntSummaryStatistics::new, IntSummaryStatistics::accept, 
	                    IntSummaryStatistics::combine);
		
		IntSummaryStatistics istats2 = orderEntries.stream().
	            				collect( 
	            						() -> new IntSummaryStatistics(),
	            						(i,o) -> i.accept(o.getAmount()),
	            						(i1, i2) -> i1.combine(i2));
		System.out.println("istats2 2 = "+istats2);
		
		
		//Data set1 = new Data(new double[]{1, 2, 3, 4, 5});
	    //System.out.println("Set1: " + set1);
	    
	    Data setss = new Data(Stream.of("1.2","2","3.5","4","5"));
		System.out.println("d list = "+setss.d);
		
		groupByAndLimit();
		
		flatMapTest();
		
		groupByRange();
		
		sort2DArray(true);
		
		
	}
	
	
	
	public static void sort2DArray(boolean sortByFirstName) {
		System.out.println("***** Sorting 2D Array *****");
		String[][] arr = {{"Bill","Jones"}, 
						    {"Janet","Kline"},
						    {"George","Bailey"}, 
						    {"Ellan","Sanches"}, 
						    {"Tom","Nguyen"}};
		List<String> l =  convertToList(arr);
		System.out.println("List = "+l.toString());
		if(sortByFirstName)
			Collections.sort(l, (s1, s2) -> s1.split(" ")[0].compareTo(s2.split(" ")[0]));
		else
			Collections.sort(l, (s1, s2) -> s1.split(" ")[1].compareTo(s2.split(" ")[1]));
		System.out.println("Sorted List = "+l.toString());
	}
	
	public static List<String> convertToList(String[][] arr){
		List<String> l8 = Arrays.asList(arr).stream().map(a -> a[0] + " "+ a[1]).collect(Collectors.toList());
		return l8;
	}
	
	private static void flatMapTest() {
		System.out.println("*****Flat Map Test*****");
		Map<String, List<String>> people = new HashMap<>();
		people.put("John", Arrays.asList("555-1123", "555-3389"));
		people.put("Mary", Arrays.asList("555-2243", "555-5264"));
		people.put("Steve", Arrays.asList("555-6654", "555-3242"));
		 
		List<String> phones = people.values().stream()
		  .flatMap(Collection::stream)
		    .collect(Collectors.toList());
		
		System.out.println("Flat Map Result "+phones); 
		
	}
	
	public static void groupByAndLimit() {
		System.out.println("Calling group and limit ------");
		List<Course> courses = new ArrayList<Course>();
		for(int i=0; i<10 ; i++) {
			Course c = new Course();
			c.setCourseId(i);
			c.setCourseName("A "+i);
			Teacher t = new Teacher();
			if(i%2 == 0) {
				t.setName("T "+i);
				t.setTeacherId(i);
			}else {
				t.setName("Hello");
			}
			c.setTeacher(t);
			courses.add(c);
		}
		
		
		
		
		
		Map<Integer, List<Course>>  result = courses.stream()
		        .collect(Collectors.groupingBy(c -> c.getTeacher().getTeacherId(), 
		        		Collector.of(
		    	                ArrayList :: new, 
		    	                (list, elem) -> { 
		    	                						if (list.size() < 2) 
		    	                							list.add(elem); 
		    	                					}, 
		    	                (list1, list2) -> {
		    	                		 list1.addAll(list2);
		    	                    return list1;
		    	                }
		    	           )));
		
		

		
		for(Entry<Integer, List<Course>> en : result.entrySet()) {
			int in = en.getKey();
			List<Course> cours  = en.getValue();
			System.out.println("key = "+in + " , List : "+cours);
		}
		
		//System.out.println("result = "+result);
		
		
		
		
	}
	
	private static <T> Collector<T, ?, List<T>> limitingList(int limit) {
	    return Collector.of(
	                ArrayList::new, 
	                (l, e) -> { if (l.size() < limit) l.add(e); }, 
	                (l1, l2) -> {
	                    l1.addAll(l2.subList(0, Math.min(l2.size(), Math.max(0, limit - l1.size()))));
	                    return l1;
	                }
	           );
	}
	
	public static void groupByRange() {
		List<MyBigDecimal> bigDecimals = new ArrayList<MyBigDecimal>();
		for(int i =0; i<= 10; i++) {
			MyBigDecimal md = new MyBigDecimal();
			if(i>0 && i<= 2)
				md.setRange(1);
			else if(i>2 && i<= 5)
				md.setRange(2);
			else if(i>5 && i<= 7)
				md.setRange(3);
			else
				md.setRange(4);
			md.setValue(i);
			
			bigDecimals.add(md);
		}
		
		
		Map<Integer, List<MyBigDecimal>>  result = bigDecimals.stream()
		        .collect(Collectors.groupingBy(e -> e.getRange(), 
		        		Collector.of(
		    	                ArrayList :: new, 
		    	                (list, elem) -> { 
		    	                						if (list.size() < 2) 
		    	                							list.add(elem); 
		    	                					}, 
		    	                (list1, list2) -> {
		    	                		 list1.addAll(list2);
		    	                    return list1;
		    	                }
		    	           )));
		
		for(Entry<Integer, List<MyBigDecimal>> en : result.entrySet()) {
			int in = en.getKey();
			List<MyBigDecimal> cours  = en.getValue();
			System.out.println("Key Range = "+in + " , List Size : "+cours.size());
		}
		
		
	}

}


class MyBigDecimal{
	private int range;
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
}

 class Course{
    public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	private int courseId;
    private String courseName;
    private Teacher teacher;
}

 class Teacher{
    private int teacherId;
    private String name;
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

class Data {
	List<Double> d;
	
	public Data(Stream sets) {
         this.d = (List<Double>) sets.collect(Collectors.toList());
    }
}

class OrderEntry {

    int amount;

    public OrderEntry(int amount) {
        super();
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}



class Foo{
	String name;
	List<Bar> bars = new ArrayList<Bar>();
	
	Foo(String name){
		this.name = name;
	}
}

class Bar{
	String name;
	
	Bar(String name){
		this.name = name;
	}
}

class MyCustomCollector implements Collector<Person, StringJoiner, String>{
	private final static Set<Characteristics> EMPTY = Collections.emptySet();
	
	@Override
	public Supplier<StringJoiner> supplier() {
		// TODO Auto-generated method stub
		return () -> new StringJoiner("|");
	}

	@Override
	public BiConsumer<StringJoiner, Person> accumulator() {
		// TODO Auto-generated method stub
		return (joiner,person) -> joiner.add(person.name.toUpperCase());
	}

	@Override
	public BinaryOperator<StringJoiner> combiner() {
		// TODO Auto-generated method stub
		return (joiner1, joiner2) -> joiner1.merge(joiner2);
	}

	@Override
	public Function<StringJoiner, String> finisher() {
		// TODO Auto-generated method stub
		return StringJoiner::toString;
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		// TODO Auto-generated method stub
		
		return EMPTY;
//		return EnumSet.of(Collector.Characteristics.CONCURRENT, Collector.Characteristics.IDENTITY_FINISH);
	}
	
}

class Person implements Comparable<Object>{
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public int compareTo(Object obj){
        int returnValue;
        if(age ==((Person) obj).age)
            returnValue=0;
        else
            if(age >((Person) obj).age)
                returnValue = 1;
            else
                returnValue =-1;
         
        return returnValue;
    }
     
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Person))
            return false;
         
        return (age == ((Person) obj).age); 
    }
     
    public int hashCode()
    {  
        return name.hashCode();  
    } 
	
}

