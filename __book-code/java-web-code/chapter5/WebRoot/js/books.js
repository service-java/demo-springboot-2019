
//�������ʾһ���㣬�ò������Ϊdiv1������ 
function mousePos(e){//������ȡ��ǰ������꣬
var x,y;
var e = e||window.event;
return {
x:e.clientX+document.body.scrollLeft+document.documentElement.scrollLeft,
y:e.clientY+document.body.scrollTop+document.documentElement.scrollTop
};
};
function showTip(none) 
{ 
var div1 = document.getElementById('div'+none); //��Ҫ�����Ĳ� 
div1.style.display="block"; //div1��ʼ״̬�ǲ��ɼ��ģ����ÿ�Ϊ�ɼ� 
//window.event�����¼�״̬�����¼�������Ԫ�أ�����״̬�����λ�ú���갴ť״. 
//clientX�����ָ��λ������ڴ��ڿͻ������ x ���꣬���пͻ����򲻰�����������Ŀؼ��͹������� 
div1.style.left=mousePos(event).x; 
div1.style.top=mousePos(event).y-110; //���Ŀǰ��X���ϵ�λ�ã���10��Ϊ�����ϱ��ƶ�110��px���㿴������ 
div1.style.position="absolute"; //����ָ��������ԣ�����div1���޷�������궯 
}

//�رղ�div1����ʾ 
function closeTip(none) 
{ 
var div1 = document.getElementById('div'+none); 
div1.style.display="none"; 
} 