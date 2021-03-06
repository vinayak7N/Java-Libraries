
## JVM Internals
In this article concepts regarding internal architecture of Java Virtual Machine is described.

![JVM Internal Architecture](/images/jvm-internal.jpg "JVM Internal Architecture")

## Technology
* JAVA: 1.8

## Table of Contents
* [Different Modules of JVM](#different-modules-of-jvm-are)
* [Class Loader Subsystem](#class-loader-subsystem)
  * [Loading](#1-loading)
  * [Linking](#2-linking)
  * [Initialization](#3-initialization)
* [Types of Class loaders](#types-of-class-loaders)
* [Bootstrap Class Loader](#1-bootstrap-class-loader--primordial-class-loader)
* [Extension Class Loader](#2-extension-class-loader)
* [Application Class Loader](#3-application-class-loader--system-class-loader)
* [How Class Loader work](#how-class-loader-work)
* [Need for Customised Class loader](#need-for-customised-class-loader)
* [Various Memory Areas inside JVM](#various-memory-areas-inside-jvm)
  * [Method Area](#1-method-area)
  * [Heap Area](#2-heap-area)
  * [Stack Area](#3-stack-area)
    * [Stack Frame Structre](#stack-frame-structure)
  * [PC Registers](#4-pc-registersprogram-counter-registers)
  * [Native Method Stacks](#5-native-method-stacks)
* [Execution Engine](#execution-engine)
  * [Interpreter](#1-interpreter)
  * [JIT Compiler](#2-jit-compiler)
* [Java Native Interface](#java-native-interfacejni-)
* [Class File Structure](#class-file-structure)
* [Programs](#programs)


### Different modules of JVM are:
  + Class Loader subsystem
  + Differnt Memory Areas
  + Execution Engine
  + Java Native Interface(JNI)
  + Native Method Libraries

### Class Loader subsystem

Operations/Responsibility:
* Linking
* Loading
* Initialization

#### 1. Loading:
Read java class files from HardDisk and store corresponding data in to Method area of JVM.  
It stores following information:  
* Fully qualified name of class.
* Fully qualified name of immediate parent class.
* Methods and Variable info.
* Constructor info.
* Modifiers info.
* Constant pool info, etc.

After loading, JVM will create a Class type object of these loaded classes and store their object inside the JVM Heap area(not the actual object of Student but the Class type object of Student in Heap).
These Class type object will be helpful for programmers to get various class related information like no.of methods, no. of variables, etc.

#### 2. Linking:

It consist of 3 activities:
##### 1. Verification 
Internally ByteCode verifier is responsible for verification of generated bytecode and it verifies whether it is generated by valid compile, is bytecode in proper format(Structurally correct). ByteCode verifier is a part of the Class Loader subsystem. If verification fails we get java.lang.VerifyError. This makes java secure.
##### 2. Preparation
It helps to allocate memory for static variables and assign default values to them. In the Initialization phase, the original values of static variables are assigned.
##### 3. Resolution
It is the process of replacing symbolic names in our program with original memory references from the method area.

For example;

      Class Test{
       public static void main(String[] args){
        String s = new String(“Abc”);
        Student student  =new Student();
      }
    }

For above Test class, the names of child classes used will be stored in the constant pool of Test class. Here, Test,String, Student are symbolic names which will be resolved with original method reference.

#### 3. Initialization:
		
In this phase static variables are initialized with their original values and static blocks are also executed from parent to child and from top to bottom.
While loading or linking or initializing if there is any error occur then error - java.lang.LinkingError is thrown and VerifyError is the child class of LinkingError.

![Class Loading Process](/images/class-loading-process.jpg "Class Loading Process")

#### Types of Class Loaders:
	
##### 1. Bootstrap Class Loader / Primordial Class Loader
Responsible for loading JDK\JRE\lib\rt.jar which has all core java api classes and JDK\JRE\lib\ this location  is considered as Bootstrap class path. It is by default available in every JVM and mostly implemented in C/C++ not in java language.

##### 2. Extension Class Loader
Responsible for load classes from extension classpath JDK\JRE\lib\ext\*.jar which if contains any library it will be loaded. Extension class loader is the child class of BootStrap class loader. This class is implemented in Java and the corresponding implementation class file name is sun.misc.Launcher$ExtClassLoader.class.($ means it is an inner class).

##### 3. Application Class Loader / System Class Loader
It is a child of Extension class loader. Responsible for loading classes from application classpath it internally uses environment variable classpath.
It is implemented in Java and the corresponding implementation class file name is sun.misc.Launcher$AppClassLoader.class.

#### How Class Loader work?

1. Class loader follows Delegation Hierarchy Principal.
2. JVM checks if the corresponding Student.class file is already loaded in the Method area then it will consider the loaded class else it requests the Class Loader Subsystem.
3. CLSs forward this request to Application Class Loader.
4. ACL forward this request to Extension Class Loader.
5. ECL forwards this request to Bootstrap Class Loader.
6. BCL searches for a class in Bootstrap class path, if it is found the class is returned otherwise reverse delegation is processed in which BCL delegates its request back to ECL and ECL after searching in its classpath and could not be able to find the requested class then delegates its request to ACL and if it is not found there as well then ClassNotFoundException or NoClassDefFoundError is thrown.
7. While searching highest priority for BCL->ECL->ACL.

#### Need for Customised Class loader

* Default class loader : load .class file only once even though we are using multiple times.
After loading .class file if it is modified outside default class loader it won't load updated version of class file because .class file already available in method area. Resolve the problem by defining our own customised class loader . 
* Customized Class loader : Advantage : we can control class loading mechanism based on our requirement. eg: we can load .class file by checking every time whether class file is modified or not.If it is modified then load the updated version to our program else use the same class file.
To define our own class loader it must be a child class of java.lang.classloader as all implemented class loaders are already child of it.

      public class CustomisedClassLoader extends ClassLoader{
        public Class loadClass(String className) throws ClassNotFoundException{
	        //pseudo code
  	      Check for updates and load updated .class file
  	      And return the corresponding class.
	        If class not found then throws exception.
        }
      } 
    
To use the above customised class loader:

      public class Client{
        public static void main(String[] args){
	        //pseudo code
	        Student s1 = new Student();//loaded by default class loader
	    	  CustomisedClassLoader cl = new CustomisedClassLoader ();
	        cl.loadClass(“Student”);
          Student s2 = new Student();//loaded by customised class loader
          And then again whenever creating object we can call it 
          To provide the latest class file.
        }
      } 

Server app developers while designing web/app servers generally define this customised class loader so that with every new update new class files are loaded.

#### Various Memory Areas inside JVM

##### 1. Method Area
* For every JVM, one method area is available.
* At the time of JVM, startup one method area will be created.
* Total .class level binary info will be stored in the Method area.
* And corresponding static variables will also be stored here.
* Constant pools of a class will be stored here as well.
* There is only one method area per JVM and multiple threads can access it simultaneously so it is not thread safe.
* Method area need not be a continuous memory area allocated inside JVM.

##### 2. Heap Area
* For every JVM, one heap area is available.
* At the time of JVM, startup one heap area will be created.
* Objects are stored in heap so all instance variables(object level data) are stored here.
* Heap area data is globally accessible to all the threads that's why it is also not thread safe.
* Arrays are objects in java so arrays are also here.
* Heap area need not be a continuous memory area allocated inside JVM.



##### 3. Stack Area
* For every thread JVM will create a separate runtime stack inside Stack memory.
* Runtime Stack is not for JVM but for every thread present in the Stack memory area.
* After thread work is done, allocated stack memory is destroyed by JVM just before terminating the thread.
* Each entry in the stack during thread operation is called Stack Frame/Activation record.
* The data stored for any thread inside the runtime stack is not available to any other thread.So, the data is thread safe.

###### Stack Frame Structure:
Each Stack frame consist of 3 parts:
* Local variable array
It contains all methods parameters(if any) and all declared local variables. Each memory area of the local memory area is of 4 bytes. Int, Float and Object are of 4 bytes so they occupy one slot each while long and double is of 8 bytes so they need 2 slots each in array. byte, short and char are promoted to 4 bytes i.e. int before storing. And for boolean we can’t expect, it depends from JVM to JVM but most of them follow 1 slot.
* Operand stack
It acts as a workspace for JVM to store any intermediate calculation result. Some instructions can push values to the operand stack and some instructions can pop values from the operand stack, some can perform required operations.Example instructions like iload_0, iload_1, iadd, istore_2.
* Frame data
It consists of a constant pool of all symbolic references used in the stack local array(or method) including reference to exception table which provides corresponding catch block information in case of exceptions. It's like meta data for executing thread on a block of code.


##### 4. PC Registers(Program Counter Registers): 
* Internally used by JVM not for programmers.
* For every thread, JVM will create a PC register.
* PCR holds the address of the current executing instruction once the instruction completes PCR gets incremented to the next instruction.

##### 5. Native Method Stacks:
* For every thread JVM will create a seperate NMS.
* For normal java methods, runtime stack is created but for native methods a separate NMC is created to hold the method reference. 
* For every JVM ---> One Heap area, One Method area.
* For every Thread ---> One Stack area, One PC register, One Native method stack.
* Static variables ---> Method area
* Instance variables ---> Heap area
* Local variables ---> Stack area

For this program following memory areas will be allocated:
      class Test {
	      Student s1 = new Student();
	      Static Student s2 = new Student();
	      public static void main(String[] args){
	        Test t = new Test();
	        Student s3 = new Student();
        }
      }

![Object Storage](/images/object-internal-storage.jpg "Object Storage")

A Java application can communicate using Runtime object and Runtime is a singleton class.
So to get runtime instance we can call:
	Runtime runtime = Runtime.getRuntime();

#### Execution Engine:
It is a central component of JVM.Execution engine is responsible for executing Java class files. It consist of two modules:
##### 1. Interpreter:
It is responsible to read bytecode and interpret into machine code(native code). And execute mc code line by line. The problem with interpreter is it interprets every  time even if the same method is invoked multiple times which reduces performance of the system. To overcome this problem Sun programmers introduced JIT compiler in 1.1v.
		
##### 2. JIT compiler:
The main purpose of the JIT compiler is to improve performance. Internally JIT compiler maintains a separate count for every method. Whenever JVM come across any method call
First that method is interpreted normally by the interpreter and JIT Compiler increments the corresponding count variable. This process will be continued for every method. Once any method count reaches threshold value then the JIT compiler identifies that method is a repeatedly used method. Such methods are called HotSpot methods. Immediately JIt compiler compiles that method and generates the corresponding native code. Next time JVM comes across that method call JVM uses native code directly and executes it instead of interpreting once again. So that performance of the system will be improved.
The threshold count varies from JVM to JVM.
Some advanced JIt Compilers will recompile generated native code if count reaches threshold value second time so that more optimised mission code will be generated.
Internally Profiler(part of JIT compiler) is responsible for identifying hotspots.

##### Note:
* JVM interprets the total program at least once.
* JIT compilation is only for repeatedly called methods not for every method call.

#### Java Native Interface(JNI) :
Execution engine to execute native methods needs Java Native Interface(JNI) to call Native libraries.
It provides information regarding native libraries to the execution engine.
Native method library hold info regarding native libraries info.

#### Class File Structure:

        classFile{
          magic_number;
          minor_version;
          major_version;
          constant_pool_count;
          constant_pool[];
          access_flags;
          this_class;
          super_class;
          interface_count;
          interface[];
          fields_count;
          fields[];
          method_counts;
          methods[];
          attributes_count;
          attributes[];
        }

JVM will store this structure in the method area.
* Magic number: The first 4 bytes of generated .class file is magic number, this is a predefined value used by JVM to identify .class file is generated by valid compiler or not. The value should be 0XCAFEBABE. Whenever we are executing a java class, if JVM is unable to find a valid magic number then we will get a Runtime exception as java.lang.ClassFormatError: Incompatible magic value.
* Minor version and Major version: They represent .class file versions, JVM will use these versions to identify which version of compiler generates current .class file. Represented by M.m where M-->Major version and m→ minor version. For 1.5v M.m is 49.0, for 1.6v-->50.0, for 1.7v-->51.0 values. Note: Lower version compiler generated .class files can be run by higher version JVM but higher compiler generated .class files can’t be run by lower version JVMs. For example, if a java program is compiled in 1.6v it can be run 1.7v but not vice-versa. Otherwise we will get a runtime exception as java.lang.UnsupportedClassVersionError: unsupported major.minor version.
* constant_pool_count: It represents the number of constants present in constant pool.
* constant_pool[]: It represents info about constants present in the constant pool.
* access_flags: It provides info about modifiers which are declared to the class.
* this_class: It represents the fully qualified name of the class.
* super_class: It represents the fully qualified name of the immediate parent class.
* interface_count:  It returns no. of interfaces implemented by current class.
* interface[]: It returns interfaces info implemented by current class.
* fields_count: It represents no. of static fields present in a class.
* fields[]:  It represents an array of static fields info present in a class.
* method_counts: It represents no. of methods present in current class.
* methods[]: It provides info about all methods present in the current class.
* attributes_count: It returns no. of attribute(instance variable) present in current class.
* attributes[]: It provides info about all attributes(instance variable) in current class.

##### javap -verbose Test.class
It helps to provide info regarding class structure attributes.

### Programs


* [ClassLoadingProgram](/JVMInternals/src/ClassLoadingProgram.java)
Retrieved Class type of object representing student and stored in Heap Area.

* [ClassLoadingProgram2](/JVMInternals/src/ClassLoadingProgram2.java)
This program demonstrates that only one Class class type object is created for a particular object irrespective of a user creating multiple instance of a class.

* [HeapMemoryStatisticsProgram](/JVMInternals/src/HeapMemoryStatisticsProgram.java)
This program shows how we can fetch/update heap memory size.
