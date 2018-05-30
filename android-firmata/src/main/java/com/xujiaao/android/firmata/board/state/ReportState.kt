package com.xujiaao.android.firmata.board.state

class ReportState {

    private val mRegistry = HashMap<String, HashSet<Any>>()

    /**
     * Check whether any report with the specialized id are enabled.
     */
    @Suppress("unused")
    fun isReportEnabled(id: String): Boolean = mRegistry[id]?.isEmpty() ?: true

    /**
     * Sets whether a report with the specialized id and token is enabled.
     *
     * @return True, if any report with the specialized id are enabled. Otherwise, false.
     */
    fun setReportEnabled(id: String, token: Any, enabled: Boolean): Boolean {
        return if (enabled) {
            mRegistry.getOrPut(id) { HashSet() }.add(token)
            true
        } else {
            val registry = mRegistry[id]
            registry?.remove(token)
            registry?.isNotEmpty() ?: false
        }
    }
}