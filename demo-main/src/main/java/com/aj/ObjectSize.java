package com.aj;

import sun.misc.VM;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

public class ObjectSize {

	private final VirtualMachine vm;

	private final Set<Object> visited;
	private final Object[] roots;

	public ObjectSize(Object... roots) {
		this.roots = roots;
		this.visited = Collections.newSetFromMap(new IdentityHashMap<Object, Boolean>());
		this.vm = VM.current();
	}

	public long calculateSize() {
		long size = 0;
		List<Object> curLayer = new ArrayList<Object>();
		List<Object> newLayer = new ArrayList<Object>();

		for (Object root : roots) {
			visited.add(root);
			size += size(root);
			curLayer.add(root);
		}

		while (!curLayer.isEmpty()) {
			newLayer.clear();
			for (Object next : curLayer) {
				for (Object ref : peelReferences(next)) {
					if (visited.add(ref)) {
						size += size(ref);
						newLayer.add(ref);
					}
				}
			}
			curLayer.clear();
			curLayer.addAll(newLayer);
		}

		return size;
	}

	private long size(Object obj) {
		return obj != null ? vm.sizeOf(obj) : 0;
	}

	private static List<Object> peelReferences(Object o) {

		if (o == null) {
			// Nothing to do here
			return Collections.emptyList();
		}

		if (o.getClass().isArray() && o.getClass().getComponentType().isPrimitive()) {
			// Nothing to do here
			return Collections.emptyList();
		}

		List<Object> result = new ArrayList<Object>();

		if (o.getClass().isArray()) {
			for (Object e : (Object[]) o) {
				if (e != null) {
					result.add(e);
				}
			}
		} else {
			for (Field f : getAllReferences(o.getClass())) {
				try {
					f.setAccessible(true);
				} catch (Exception e) {
					// Access denied
					result.add(null);
					continue;
				}

				try {
					Object e = f.get(o);
					if (e != null) {
						result.add(e);
					}
				} catch (IllegalAccessException e) {
					throw new IllegalStateException(e);
				}
			}
		}

		return result;
	}

	private static Collection<Field> getAllReferences(Class<?> klass) {
		List<Field> results = new ArrayList<Field>();

		for (Field f : klass.getDeclaredFields()) {
			if (Modifier.isStatic(f.getModifiers()))
				continue;
			if (f.getType().isPrimitive())
				continue;
			results.add(f);
		}

		Class<?> superKlass = klass;
		while ((superKlass = superKlass.getSuperclass()) != null) {
			for (Field f : superKlass.getDeclaredFields()) {
				if (Modifier.isStatic(f.getModifiers()))
					continue;
				if (f.getType().isPrimitive())
					continue;
				results.add(f);
			}
		}

		return results;
	}

	public static long sizeOf(Object obj) {
		return new ObjectSize(obj).calculateSize();
	}

}