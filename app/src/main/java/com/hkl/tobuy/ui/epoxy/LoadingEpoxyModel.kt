import com.hkl.tobuy.R
import com.hkl.tobuy.databinding.EpoxyModelLoadingBinding
import com.hkl.tobuy.ui.epoxy.ViewBindingKotlinModel


class LoadingEpoxyModel :
    ViewBindingKotlinModel<EpoxyModelLoadingBinding>(R.layout.epoxy_model_loading) {

    override fun EpoxyModelLoadingBinding.bind() {
        // nothing to do
    }
}