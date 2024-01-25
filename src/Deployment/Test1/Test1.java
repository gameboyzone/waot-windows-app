import ca.beq.util.win32.registry.*; 

class Test1
{
RegistryKey r;
RegistryValue v;
	
	Test1()
	{
		try
		{
		//Create a Key
		r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Gurucool");
		r.create();
		System.out.println("Key Created successfully");
		
		//Create a Sub-Key
		r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Gurucool");
		r.createSubkey("Hello");
		System.out.println("Sub-Key Created successfully");
		
		//Deleting an existing Key
		/*r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Gurucool\\Hello");
		r.delete();
		System.out.println("Key Deleted successfully");*/
		
		//Writing A Value
		RegistryKey r=new RegistryKey(RootKey.HKEY_CURRENT_USER, "Software\\Gurucool\\Hello");
		v=new RegistryValue("Guru",ValueType.REG_DWORD,new Integer(0x00000001));
		r.setValue(v);
		v=new RegistryValue("GG",ValueType.REG_SZ,"Guru says Lolz...");
		r.setValue(v);
		System.out.println("Registry Value Written");
		
		//Reading a value
		r=new RegistryKey(RootKey.HKEY_CURRENT_USER, "Software\\Gurucool\\Hello");
			
			if(r.hasValue("GG"))
			{
   				RegistryValue v = r.getValue("GG");
   				System.out.println("Value: "+v.toString());	//v.getData().toString() will give the Data
			}
			
		//Deleting a value
		r=new RegistryKey(RootKey.HKEY_CURRENT_USER,"Software\\Gurucool\\Hello");
		r.deleteValue("GG");
		System.out.println("Value Deleted successfully");
		}
		catch(Exception e4)
		{
			e4.printStackTrace();
		}
    }
    
    public static void main(String[] args) 
    {
    	Test1 t1=new Test1();
    }
}