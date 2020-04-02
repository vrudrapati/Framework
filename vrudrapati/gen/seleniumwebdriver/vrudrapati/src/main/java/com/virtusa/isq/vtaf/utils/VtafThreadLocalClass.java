package com.virtusa.isq.vtaf.utils;

public final class VtafThreadLocalClass {
	/** ThreadLocal variable */
	public static ThreadLocal<TestCaseUnit> vtafThreadLocal = new ThreadLocal<TestCaseUnit>();

	/** Sets the TestCaseUnit class object */
	public static void setTestCaseUnit(final TestCaseUnit mTestCaseUnit) {
		vtafThreadLocal.set(mTestCaseUnit);
	}

	/** used to remove TestCaseUnit object */
	public static void unsetTestCaseUnit() {
		vtafThreadLocal.remove();
	}

	/** Returns the TestCaseUnit object */
	public static TestCaseUnit getTestCaseUnit() {
		return vtafThreadLocal.get();
	}
}
