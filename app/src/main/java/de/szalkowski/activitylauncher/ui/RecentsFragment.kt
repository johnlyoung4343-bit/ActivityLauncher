package de.szalkowski.activitylauncher.ui

import android.content.ComponentName
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import de.szalkowski.activitylauncher.R
import de.szalkowski.activitylauncher.databinding.FragmentRecentsBinding
import de.szalkowski.activitylauncher.services.MyActivityInfo

class RecentsFragment : Fragment() {
    private var _binding: FragmentRecentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MockListAdapter()
        binding.rvRecents.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class MockListAdapter : RecyclerView.Adapter<MockListAdapter.ViewHolder>() {

        private val mockActivities = listOf(
            MyActivityInfo(
                ComponentName("com.google.android.youtube", "com.google.android.youtube.app.honeycomb.Shell\$HomeActivity"),
                "YouTube",
                android.graphics.drawable.ColorDrawable(0), // Placeholder
                null,
                false
            ),
            MyActivityInfo(
                ComponentName("com.android.deskclock", "com.android.deskclock.DeskClock"),
                "Clock",
                android.graphics.drawable.ColorDrawable(0), // Placeholder
                null,
                false
            )
        )

        inner class ViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
            val tvName: TextView = viewItem.findViewById(R.id.tvName)
            val tvClass: TextView = viewItem.findViewById(R.id.tvClass)
            val ivIcon: ImageView = viewItem.findViewById(R.id.ivIcon)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.list_item_activity_list, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = mockActivities[position]
            holder.tvName.text = item.name
            holder.tvClass.text = item.componentName.shortClassName
            holder.ivIcon.setImageResource(R.drawable.ic_launcher_foreground)
        }

        override fun getItemCount(): Int = mockActivities.size
    }
}
