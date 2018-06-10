package com.xujiaao.android.firmata.sample

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.BaseExpandableListAdapter
import android.widget.EditText
import android.widget.TextView
import com.xujiaao.android.firmata.toolbox.AndroidThingsCompat
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*
import kotlin.collections.LinkedHashMap

const val REQUEST_CODE_ENABLE_BT = 1

class SampleListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        expandableListView {
            setAdapter(SampleListAdapter(
                context = this@SampleListActivity,
                groups = getSampleGroups(),
                onEntryClick = {
                    val intent = Intent()
                    intent.component = ComponentName(packageName, it.activity)
                    startActivity(intent)
                }
            ))
        }

        if (AndroidThingsCompat.isAndroidThings) {
            BluetoothAdapter.getDefaultAdapter()?.let { bluetoothAdapter ->
                if (!bluetoothAdapter.isEnabled) {
                    startActivityForResult(
                        Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),
                        REQUEST_CODE_ENABLE_BT
                    )
                }
            }
        }
    }

    private fun getSampleGroups(): List<SampleGroup> {
        val intent = Intent(Intent.ACTION_VIEW)
            .setPackage(packageName)
            .addCategory(Intent.CATEGORY_SAMPLE_CODE)

        return packageManager.queryIntentActivities(intent, PackageManager.GET_META_DATA).map {
            SampleEntry(
                it.activityInfo.loadLabel(packageManager).toString(),
                it.activityInfo.metaData?.getString("group") ?: throw IllegalStateException(
                    "Failed to startUpdating group from '${it.activityInfo.name}'"
                ),
                it.activityInfo.name
            )
        }.groupByTo(LinkedHashMap() /* TreeMap() */) {
            it.group
        }.mapTo(ArrayList()) {
            SampleGroup(it.key, it.value)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (AndroidThingsCompat.isAndroidThings) {
            if (requestCode == REQUEST_CODE_ENABLE_BT && resultCode == Activity.RESULT_OK) {
                toast(R.string.message_bluetooth_enable)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_smaple_list, menu)

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.transport).title = getPreferredTransport()
        menu.findItem(R.id.auto_connect).isChecked = isAutoConnectEnabled()

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var handled = true
        when (item.itemId) {
            R.id.transport -> showEditTransportDialog()
            R.id.reset -> setPreferredTransport(DEFAULT_TRANSPORT)
            R.id.auto_connect -> setAutoConnectEnabled(!isAutoConnectEnabled())
            else -> handled = false
        }

        return handled || super.onOptionsItemSelected(item)
    }

    private fun showEditTransportDialog() {
        alert(Appcompat) {
            title = getString(R.string.dialog_transport_title)

            var input: EditText? = null
            customView {
                verticalLayout {
                    padding = dip(20)
                    input = editText {
                        hint = DEFAULT_TRANSPORT
                        append(getPreferredTransport())
                    }
                }
            }

            okButton {
                input?.let {
                    setPreferredTransport(it.text.toString())
                }
            }
        }.show()
    }
}

private data class SampleGroup(val title: String, val entries: List<SampleEntry>)

private data class SampleEntry(val title: String, val group: String, val activity: String)

private class SampleListAdapter(
    context: Context,
    private val groups: List<SampleGroup>,
    private val onEntryClick: (SampleEntry) -> Unit
) : BaseExpandableListAdapter() {

    private val mLayoutInflater = LayoutInflater.from(context)

    override fun hasStableIds(): Boolean = false

    override fun getGroupCount(): Int = groups.size
    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()
    override fun getGroup(groupPosition: Int): SampleGroup = groups[groupPosition]

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View = (convertView ?: mLayoutInflater.inflate(
        android.R.layout.simple_expandable_list_item_1, parent, false
    )).apply {
        findViewById<TextView>(android.R.id.text1).text = getGroup(groupPosition).title
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
    override fun getChildrenCount(groupPosition: Int): Int = groups[groupPosition].entries.size
    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()
    override fun getChild(groupPosition: Int, childPosition: Int): SampleEntry =
        groups[groupPosition].entries[childPosition]

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View = (convertView ?: mLayoutInflater.inflate(
        android.R.layout.simple_expandable_list_item_1, parent, false
    )).apply {
        val entry = getChild(groupPosition, childPosition)
        tag = entry
        onClick {
            onEntryClick(entry)
        }

        findViewById<TextView>(android.R.id.text1).text = entry.title
    }
}