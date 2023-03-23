package edu.uncc.assessment06;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.DoubleValue;
import com.squareup.picasso.Picasso;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.HashMap;

import edu.uncc.assessment06.databinding.CartRowItemBinding;
import edu.uncc.assessment06.databinding.FragmentCartBinding;
import edu.uncc.assessment06.databinding.ProductRowItemBinding;

public class CartFragment extends Fragment {

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentCartBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CartAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    ArrayList<Product> mProducts = new ArrayList<>();
    ListenerRegistration listenerRegistration;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Cart");
        adapter = new CartAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        listenerRegistration = db.collection("cart")
                .whereEqualTo("owner_id", FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

                        }else{
                            for(QueryDocumentSnapshot doc : value){
                                Product product = doc.toObject(Product.class);
                                mProducts.add(product);
                            }
                            adapter.notifyDataSetChanged();

                        }
                    }
                });

    }
    private void calculateTotal(){
        if(mProducts.size() == 0){
            binding.textViewTotal.setText("$0.00");
        }else {
            double total = 0.0;
            for (Product product: mProducts){
                total = total + Double.parseDouble( product.getPrice());
            }
            binding.textViewTotal.setText("$" + String.format("%.2f", total));

        }
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        if(listenerRegistration != null){
            listenerRegistration.remove();
        }
    }

    class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
        @NonNull
        @Override
        public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            CartRowItemBinding rowBinding = CartRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new CartViewHolder(rowBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull CartViewHolder holder, int position){
            Product product = mProducts.get(position);
            holder.setupUI(product);
        }

        @Override
        public int getItemCount(){
            return mProducts.size();
        }

        class CartViewHolder extends RecyclerView.ViewHolder{
            CartRowItemBinding mBinding;
            Product mProduct;
            public CartViewHolder(@NonNull CartRowItemBinding rowBinding){
                super(rowBinding.getRoot());
                this.mBinding = rowBinding;
            }

            public void setupUI(Product product){
                this.mProduct = product;
                mBinding.textViewProductName.setText(mProduct.getName());
                mBinding.textViewProductPrice.setText(mProduct.getPrice());
                Picasso.get().load(product.getImg_url()).into(mBinding.imageViewProductIcon);
                listenerRegistration = db.collection("cart")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                if(error != null){
                                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

                                }else{
                                    for(QueryDocumentSnapshot doc : value){
                                        Product product = doc.toObject(Product.class);
                                        mProducts.add(product);
                                    }
                                    adapter.notifyDataSetChanged();

                                }
                            }
                        });

            }
        }
    }



}