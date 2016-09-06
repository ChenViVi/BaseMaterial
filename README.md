# BaseMaterial

简介:封装一些常用的视图模板和基本框架，便于快速开发,老司机上路必备加油包。我们的目标是：更笨，更快，更懒！

## 使用方法（以本Demo为例）
1. 依赖工程

  [xlistview](#1.1)

  [load-image-view](#1.2)

2. \network

  [RequestMaker](#2.1)

  [PostUploadRequest](#2.2)

3. \util

  [Tool](#3.1)

4. \view

  [ConfirmDialog](#4.1)

  [DownlandDialog](#4.2)

5. \adapter

  [BaseListViewAdapter](#5.1)

  [BaseRecyclerViewAdapter](#5.2)

6. \fragment

  [BaseFragment](#6.1)

  [BaseListViewFragment](#6.2)

  [BaseDrawerFragment](#6.3)

7. \activity

  [BaseActivity](#7.1)

  [BaseListViewActivity](#7.2)

  [BaseTabTopActivity](#7.3)

  [BaseTabBottomActivity](#7.4)

### <span id="1.1">xlistview</span>
#### 简介
支持双向加载的ListView。这个是别人写的，只是为了保持控件的包名一致，方便以后复制粘贴XML，要知道由于XML中定义的控件不存在就会导致闪退的。
#### 使用说明
[直接看原作吧](https://github.com/Maxwin-z/XListView-Android)

### <span id="1.2"> load-image-view</span>
#### 简介
这个是我以前写的由Glide封装的的图片加载框架。

#### 使用说明
[直接看原作吧](https://github.com/ChenViVi/LoadImageView)

### <span id="2.1">network\RequestMaker</span>
#### 简介
这个是我以前写的由Volley封装的网络请求加载框架。
#### 使用说明
[直接看原作吧](https://github.com/ChenViVi/RequestMaker)

### <span id="2.2">network\PostUploadRequest</span>
#### 简介
用于上传文件的框架,封装于Volley,是网上找的
#### 使用说明
```
HashMap<String,String[]> map = new HashMap<>();
map.put("avatar",new String[]{imgPath, imgName);
queue.add(new PostUploadRequest("http//:111.11.11.11/uploadimg", map, new Response.Listener<JSONObject>() {
    @Override
    public void onResponse(JSONObject jsonObject) {

    }
    },
    new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(activity, "头像上传失败", Toast.LENGTH_SHORT).show();
                  }
          }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> map = new HashMap<String, String>();
                    map.put("userId","1");
                    return map;
            }
});
```

### <span id="3.1">util\Tool</span>
#### 简介
收集一些日常常用的静态方法，会持续更新。
#### 使用说明
类型 | 名称 | 参数 | 说明
----|------|----|----|
int | getScreenHeight(Context context) | 自己体会| 返回屏幕高度
int | getScreenWidth(Context context) | 自己体会| 返回屏幕宽度
int | px2dp(Context context,float value) | 自己体会| px转dp
int | dp2px(Context context,float value) | 自己体会| dp转px
int | px2sp(Context context,float value) | 自己体会| px转sp
int | sp2px(Context context,float value) | 自己体会| sp转px
String | getMD5(String info) | 自己体会| 返回md5加密后的信息

### <span id="4.1">view\ConfirmDialog</span>
#### 简介
自定义的确定取消的提示消息Dialog，主要为了Android 4.4版本以上和Android 4.4版本以下风格统一
#### 使用说明
构造器

名称 | 参数 | 说明|
------|----|----|
ConfirmDialog(Context context, String message) |<br>content=>弹出提示框的Context<br/> <br>message=>提示框显示的提示信息<br/> |

方法

类型 | 名称 | 参数 | 说明
----|------|----|----|
abstract void | onConfirm() |  | 点击确定的时候被调用
abstract void | onCancel() |  | 点击取消的时候被调用

### <span id="4.2">view\DownlandDialog</span>
#### 简介
自定义的下载文件的ProgressDialog
#### 使用说明
构造器

名称 | 参数 | 说明|
------|----|----|
DownlandDialog(Context context, File file) |自己体会|

方法

类型 | 名称 | 参数 | 说明
----|------|----|----|
abstract void | onFinish(String file) | 自己体会 | 当下载完成的时候被调用
void | onExists(String file) | 自己体会 | 需要下载的文件已存在时被调用（此时不会下载且不会显示Dialog）
void | onCancel(String file) | 自己体会 | 当用户点击返回键时被调用（默认显示提示Toast）
void | onFail(String file) | 自己体会 | 当下载失败时被调用（默认显示提示Toast）

### <span id="5.1">adapter\BaseListViewAdapter</span>
#### 简介
封装后的BaseAdapter，使得ListView的Adapter代码变得简洁，且代码格式与RecyclerView的Adapter一致。。
#### 使用说明
```
public class Article {
    private String title;

    public Article(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
```
```
public class ArticleAdapter extends BaseListViewAdapter<Article,ArticleAdapter.ViewHolder> {

    public ArticleAdapter(List<Article> data) {
        super(data);
    }

    @Override
    protected ViewHolder onCreateItem(ViewGroup viewGroup) {
        return  new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position, Article article) {
        viewHolder.tvTitle.setText(article.getTitle());
    }

    class ViewHolder extends BaseListViewAdapter.ViewHolder{
        TextView tvTitle;
        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) findViewById(R.id.tvTitle);
        }
    }
}
```

### <span id="5.2">adapter\BaseRecyclerViewAdapter</span>
#### 简介
封装后的RecyclerView.Adapter，使得RecyclerView的Adapter代码变得简洁,且代码格式与ListView的Adapter一致。
#### 使用说明
```
public class ArticleAdapter extends BaseRecyclerViewAdapter<Article,ArticleAdapter.ViewHolder> {

    public ArticleAdapter(List<Article> data) {
        super(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateItem(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position, Article article) {
        viewHolder.tvTitle.setText(article.getTitle());
    }

    class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder {
        TextView tvTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) findViewById(R.id.tvTitle);
        }
    }
}
```

### <span id="6.1">fragment\BaseFragment</span>
#### 简介
封装后的Fragment
#### 使用说明

成员变量

类型 | 名称 | 说明
----|------|----
AppCompatActivity | activity |  与当前Fragment绑定的AppCompatActivity实例
View | rootView |  当前Fragment的根布局View
RequestQueue | appQueue |  Appliaction的Volley请求队列
RequestQueue | queue |  当前Fragment的Volley请求队列

方法

类型 | 名称 | 参数 | 说明
----|------|----|----|
View| findViewById(int id) | 自己体会 | 初始化View的时候不用再调用rootView.findViewById()了，而且不用再调用view.setOnClickListener(this)了，这个方法会自动将找到的view的点击事件绑定到Fragment上
void | toast(String message) | 自己体会 | 显示一个Toast
void | startActivity(Class<?> cls) | 自己体会 | 打开一个Activity

示例
```
public class HomeFragment extends BaseFragment {

    @Override
    protected int onBindView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        findViewById(R.id.btnTabLayoutActivity)
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.btnTabLayoutActivity:
                startActivity(TabTopActivity.class);
                break;
        }
    }
}
```

### <span id="6.2">fragment\BaseListViewFragment</span>
#### 简介
只显示ListView的Fragment继承它就好了，支持双向加载 更多
#### 使用说明
成员变量

类型 | 名称 | 说明
----|------|----
ArrayList<Item> | data | ListView中的项目

方法

类型 | 名称 | 参数 | 说明
----|------|----|----|
void | clearItems() |  | 清空ListView所有项目并刷新Adapter
void | addItems(List< Item> items) | 自己体会 | 添加项目集合并刷新Adapter
void| setPullLoadEnable(boolean enable) | 自己体会 | 设定ListView是否支持上拉加载更多,默认不支持
void | setPullRefreshEnable(boolean enable) | 自己体会 | 设定ListView是否支持上下拉刷新,默认不支持
void | stopRefresh() |  | 停止下拉刷新
void | stopLoadMore() |  | 停止上拉加载更多
void | setOnItemClickListener(BaseListViewAdapter.OnItemClickListener<Item> listener) | 自己体会 | 为ListView项目添加点击事件

### <span id="6.3">fragment\BaseDrawerFragment</span>
#### 简介
作为侧栏导航的Fragment继承它就好了
#### 使用说明
方法

类型 | 名称 | 参数 | 说明
----|------|----|----|
void | setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) | <br>fragmentId=>Activity布局中将要成为侧栏导航的Fragment的id<br/><br>drawerLayout=>侧栏导航的实例<br/> <br>toolbar=>顶部工具栏的实例<br/>| 将Fragment设置为当前Activity的侧栏导航

示例
```
public class DrawFragment extends BaseDrawerFragment {
    @Override
    protected int onBindView() {
        return R.layout.fragment_drawer;
    }
}
```
```
public class DrawLayoutActivity extends BaseActivity {

    private Toolbar toolbar;
    private BaseDrawerFragment drawerFragment;

    @Override
    protected int onBindView() {
        return R.layout.activity_draw_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerFragment = (BaseDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        setSupportActionBar(toolbar);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
    }
}
```
### <span id="7.1">activity\BaseActivity</span>
#### 简介
封装后的AppCompatActivity
#### 使用说明
成员变量

类型 | 名称 | 说明
----|------|----
AppCompatActivity | activity |  与当前Activity的实例
View | rootView |  当前Activity的根布局View
RequestQueue | appQueue |  Appliaction的Volley请求队列
RequestQueue | queue |  当前Activity的Volley请求队列

方法

类型 | 名称 | 参数 | 说明
----|------|----|----|
View| findViewById(int id) | 自己体会 | 初始化View的时候不用再调用rootView.findViewById()了，而且不用再调用view.setOnClickListener(this)了，这个方法会自动将找到的view的点击事件绑定到Activity上
void | toast(String message) | 自己体会 | 显示一个Toast
void | startActivity(Class<?> cls) | 自己体会 | 打开一个Activity

示例
```
public class CollapsingToolbarLayoutActivity extends BaseActivity {

    @Override
    protected int onBindView() {
        return R.layout.activity_collapsing_toolbar_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.btn);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.btn:
                toast("onClick");
                break;
        }
    }
}
```

### <span id="7.2">activity\BaseListViewActivity</span>
#### 简介
只显示ToolBar和ListView的Activity继承它就好了，支持双向加载 更多
#### 使用说明
成员变量

类型 | 名称 | 说明
----|------|----
ArrayList<Item> | data | ListView中的项目

方法

类型 | 名称 | 参数 | 说明
----|------|----|----|
void | clearItems() |  | 清空ListView所有项目并刷新Adapter
void | addItems(List< Item> items) | 自己体会 | 添加项目集合并刷新Adapter
void| setPullLoadEnable(boolean enable) | 自己体会 | 设定ListView是否支持上拉加载更多,默认不支持
void | setPullRefreshEnable(boolean enable) | 自己体会 | 设定ListView是否支持上下拉刷新,默认不支持
void | stopRefresh() |  | 停止下拉刷新
void | stopLoadMore() |  | 停止上拉加载更多
void | setOnItemClickListener(BaseListViewAdapter.OnItemClickListener<Item> listener) | 自己体会 | 为ListView项目添加点击事件

示例
```
public class ListViewActivity extends BaseListViewActivity<Article,ArticleAdapter>{

    ArrayList<Article> items = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 20; i++){
            items.add(new Article("title"+String.valueOf(i)));
        }
        addItems(items);
        setPullRefreshEnable(true);
        setPullLoadEnable(true);
    }

    @Override
    protected ArticleAdapter setAdapter() {
        return new ArticleAdapter(data);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        clearItems();
        addItems(items);
        stopRefresh();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        addItems(items);
        stopLoadMore();
    }
}
```

### <span id="7.3>activity\BaseTabTopActivity</span>
#### 简介
显示ToolBar并需要多个顶部Tab来切换多个Fragment的Activity继承它就好了
#### 使用说明
方法

类型 | 名称 | 参数 | 说明
----|------|----|----|
void | addFragment(Fragment fragment, String title) | <br>fragment=>需要加载的Fragment<br/> <br>title=>顶部Tab上显示的标题<br/> | 加载一个Fragment

示例
```
public class TabTopActivity extends BaseTabTopActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new HomeFragment(),"HomeFragment");
        addFragment(new RecyclerViewFragment(),"RecyclerViewFragment");
        addFragment(new ListViewFragment(),"ListViewFragment");
    }
}
```

### <span id="7.4>activity\BaseTabBottomActivity</span>
#### 简介
显示ToolBar并需要多个顶部Tab来切换多个Fragment的Activity继承它就好了
#### 使用说明
方法

类型 | 名称 | 参数 | 说明
----|------|----|----|
void | addFragment(Fragment fragment, String title,int imgId) | <br>fragment=>需要加载的Fragment<br/> <br>title=>Tab上显示的标题<br/> <br>title=>Tab上显示的图标<br/> | 加载一个Fragment
void | initialise() |  | 加载完所有的Fragment需要初始化，否则不显示

示例
```
public class TabBottomActivity extends BaseTabBottomActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(new HomeFragment(),"HomeFragment",R.mipmap.ic_launcher);
        addFragment(new RecyclerViewFragment(),"RecyclerViewFragment",R.mipmap.ic_launcher);
        addFragment(new ListViewFragment(),"ListViewFragment",R.mipmap.ic_launcher);
        initialise();
    }
}
```
