import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vasupractice.databinding.RvMemesBinding
import com.example.vasupractice.load
import com.example.vasupractice.log
import com.example.vasupractice.model.Meme

class MemeAdapter(val list: List<Meme>) : RecyclerView.Adapter<MemeAdapter.VH>() {

    override fun onBindViewHolder(holder: VH, position: Int) {
        val meme = list[position]

        meme.url.log()

        holder.bind.ivMemes.load(meme.url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(RvMemesBinding.inflate(LayoutInflater.from(parent.context)))
    class VH(val bind: RvMemesBinding) : RecyclerView.ViewHolder(bind.root)
    override fun getItemCount() = list.size
}
